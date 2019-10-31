package com.wkk.dao;

import com.wkk.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @Time: 19-10-31下午4:33
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细， 可过滤重复（联合唯一主键）
     * @param seckillId
     * @param userPhone
     * @return 插入的结果及数量
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id 查询succeddkilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
