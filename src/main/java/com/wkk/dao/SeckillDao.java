package com.wkk.dao;

import com.wkk.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * @Time: 19-10-31下午4:28
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId id
     * @param killTime createtime
     * @return (需要更新多少条数据)如果影响行数>1 表示更新的记录行数
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId id
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset 偏移量
     * @param limit  限制
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);
}
