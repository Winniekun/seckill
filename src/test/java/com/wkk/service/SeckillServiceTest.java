package com.wkk.service;

import com.mysql.jdbc.log.LogFactory;
import com.wkk.dto.Exposer;
import com.wkk.dto.SeckillExecution;
import com.wkk.entity.Seckill;
import com.wkk.exception.RepeatKillException;
import com.wkk.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Time: 19-11-1下午9:06
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring 配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() {
        long seckillId = 1006;
        Seckill seckill = seckillService.getById(seckillId);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception{
        long id = 1005;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
//       exposed=true,
//       md5=5224cf731f6f306605bd802a4899d180,
//       seckillId=1005,
//       now=0, start=0, end=0}
    }

    // 集成测试代码完整逻辑 注意可重复执行
    @Test
    public void seckillLogic() throws Exception{
        long id = 1007;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}", exposer);
            long phone = 12341415311L;
            String md5 = "5224cf731f6f306605bd802a4899d180";
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e1){
                logger.error(e1.getMessage());
            }
        }else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception{
        long id = 1005;
        long phone = 12341415311L;
        String md5 = "5224cf731f6f306605bd802a4899d180";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("seckillExecution={}", seckillExecution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e1){
            logger.error(e1.getMessage());
        }


    }
}