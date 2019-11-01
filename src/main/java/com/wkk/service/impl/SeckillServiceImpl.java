package com.wkk.service.impl;

import com.wkk.dao.SeckillDao;
import com.wkk.dao.SuccessKilledDao;
import com.wkk.dto.Exposer;
import com.wkk.dto.SeckillExecution;
import com.wkk.entity.Seckill;
import com.wkk.entity.SuccessKilled;
import com.wkk.enums.SeckillStatEnum;
import com.wkk.exception.RepeatKillException;
import com.wkk.exception.SeckillCloseException;
import com.wkk.exception.SeckillException;
import com.wkk.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Time: 19-11-1下午7:21
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
// @Component(组件) @Service @Dao @Controller
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入service依赖
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    //md5盐值 用于混淆md5
    private final String slat = "aksehiucka24sf*&%&^^#^%$";


    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() ||
                nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程 不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    @Override
    @Transactional
    /**
     * 使用注解控制事物方法的优点
     * 1: 开发团队达成一致约定, 明确标注事物方法的编程风格.
     * 2: 保证事物方法的执行时间尽可能短 不要穿插其他网络操作RPC/HTTP请求或者剥离到事物方法外部
     * 3: 不是所有的方法都需要事务 如只有一条修改操作,或者只读操作不需要事物控制.
     *
     *
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillCloseException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");

        }
        // 执行秒杀逻辑： 减库存 + 记录购买行为
        Date nowTime = new Date();
        // 减库存
        int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
        try{
            if(updateCount <=0){
                // 没有更新到记录, 秒杀结束
                throw new SeckillCloseException("seckill is closed");


            }else {
                // 记录购买行为
                int inserCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                // 唯一： 联合主键
                if(inserCount <= 0){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                }else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            // 所有检查异常 转化为运行期异常
            throw new SeckillException("seckill inner error: "+ e.getMessage());

        }

    }
}
