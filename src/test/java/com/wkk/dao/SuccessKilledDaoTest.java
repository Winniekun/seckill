package com.wkk.dao;

import com.wkk.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Time: 19-10-31下午9:25
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring 配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        /**
         * 第一次： insertCount=1
         * 第二次： insertCount=0
         */
        long id = 1007L;
        long phone = 12341415311L;
        int insetCout = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount="+insetCout);

    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1007L;
        long phone = 12341415311L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}