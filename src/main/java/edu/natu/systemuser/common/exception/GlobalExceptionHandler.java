package edu.natu.systemuser.common.exception;


import edu.natu.systemuser.common.result.CommonResult;
import edu.natu.systemuser.common.result.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author LJH
 * @des 全局异常处理
 * @date 2021-09-18 10:30:01
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<String> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult<String> handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = UnAuthException.class)
    public CommonResult<String> handleUnAuthException(HttpServletResponse response, UnAuthException e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String message = e.getMessage();
        return CommonResult.failed(ResultCode.UNAUTHORIZED, message);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> handleUnKnownException(Exception e) {
        e.printStackTrace();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
        String currentDate =   dateFormat.format(new Date() );
        // 实例化一个random的对象
        Random rd = new Random();
        //为变量赋随机值1000-9999
        String randomCode = String.valueOf(rd.nextInt(8999) + 1000);
        String randomErrCode = currentDate + "-"  + randomCode;
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "系统异常【" + randomErrCode + "】，请联系系统管理员";
        }
        return CommonResult.failed(ResultCode.FAILED, message);
    }
}
