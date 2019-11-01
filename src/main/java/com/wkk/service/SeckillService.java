package com.wkk.service;

import com.wkk.dto.Exposer;
import com.wkk.dto.SeckillExecution;
import com.wkk.entity.Seckill;
import com.wkk.exception.RepeatKillException;
import com.wkk.exception.SeckillCloseException;

import java.util.List;

/**
 * 业务接口 站在“使用者”的角度设计接口
 * 三个方面：方法定义粒度 参数 返回类型（return类型/ 异常）
 *
 * @Time: 19-11-1下午6:42
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param uesrPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long uesrPhone, String md5)
    throws SeckillCloseException, RepeatKillException, SeckillCloseException;






}
