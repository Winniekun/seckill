package com.wkk.exception;

/**
 * 秒杀相关业务异常
 * @Time: 19-11-1下午7:15
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
