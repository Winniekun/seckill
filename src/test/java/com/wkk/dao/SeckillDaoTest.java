package com.wkk.dao;

import com.wkk.entity.Seckill;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Time: 19-10-31下午8:35
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test junit
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring 配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {
    // 注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @org.junit.Test
    public void queryById() {
//        long id = 1006;
//        Seckill seckill = seckillDao.queryById(id);
//        System.out.println(seckill.getName());
//        System.out.println(seckill);
    }

    @org.junit.Test
    public void queryAll() {
        /**
         * org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException:
         * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
         */
        // List<Seckill> queryAll(int offset, int limit)
        // java没有保存形参的记录

//        Seckill {seckillId=1004, number=100, startTime=Thu Oct 31 00:00:00 CST 2019, endTime=Fri Nov 01 00:00:00 CST 2019, createTime=Thu Oct 31 21:01:53 CST 2019}
//        Seckill {seckillId=1005, number=200, startTime=Thu Oct 31 00:00:00 CST 2019, endTime=Fri Nov 01 00:00:00 CST 2019, createTime=Thu Oct 31 21:01:53 CST 2019}
//        Seckill {seckillId=1006, number=300, startTime=Thu Oct 31 00:00:00 CST 2019, endTime=Fri Nov 01 00:00:00 CST 2019, createTime=Thu Oct 31 21:01:53 CST 2019}
//        Seckill {seckillId=1007, number=400, startTime=Thu Oct 31 00:00:00 CST 2019, endTime=Fri Nov 01 00:00:00 CST 2019, createTime=Thu Oct 31 21:01:53 CST 2019}
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for(Seckill seckill: seckills){
            System.out.println(seckill);
        }

    }

    @org.junit.Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1006L, killTime);
        System.out.println("updateCount=" + updateCount);
    }


}