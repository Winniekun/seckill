package com.wkk.exception;

/**
 * 秒杀关闭异常
 * @Time: 19-11-1下午7:14
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class SeckillCloseException  extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
