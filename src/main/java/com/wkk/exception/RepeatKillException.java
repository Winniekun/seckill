package com.wkk.exception;

/**
 * 重复秒杀异常(运行期异常)
 * @Time: 19-11-1下午7:10
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }


    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
