package com.example.springvue.exception;

import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO 全局异常统一处理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 14:30
 */
@ControllerAdvice
@ResponseBody
public class CommonException {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonException.class);

    @ExceptionHandler(Exception.class)
    public Result javaExceptionHandler(Exception ex) {
        LOGGER.error("Exception异常", ex);
        return Result.fail(ResultCode.FAIL);
    }
}
