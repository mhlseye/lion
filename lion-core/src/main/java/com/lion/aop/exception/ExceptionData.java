package com.lion.aop.exception;

import com.lion.core.ResultData;
import com.lion.core.common.enums.ResultDataState;
import com.lion.exception.AuthorizationException;
import com.lion.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

/**
 * @description: 统一异常处理数据返回
 * @author: Mr.Liu
 * @create: 2020-02-07 17:08
 */
public class ExceptionData {

    public static ResultData instance(final Throwable e){
        e.printStackTrace();
        ResultData resultData = new ResultData();
        resultData.setMessage(e.getMessage());
        resultData.setStatus(ResultDataState.ERROR.getKey());
        handle(e, resultData);
        return resultData;
    }

    private static void handle(final Throwable e,final ResultData resultData){
        if (e instanceof InvalidGrantException || e instanceof InternalAuthenticationServiceException){
            resultData.setMessage( "用户名/密码错误");
        }else if (e instanceof IllegalArgumentException){
            resultData.setMessage( "参数错误");
        }else if (e instanceof HttpMessageNotReadableException){
            resultData.setMessage( "数据格式错误(请出入正确的json数据)");
        }else if (e instanceof BusinessException){
            resultData.setMessage(e.getMessage());
        }else if (e instanceof AccessDeniedException){
            resultData.setMessage( "权限不足，不允许访问");
            resultData.setStatus(ResultDataState.NO_PERMISSION.getKey());
        }else if (e instanceof ConstraintViolationException){
            resultData.setMessage(e.getMessage().split(":")[1].trim());
        }else if (e instanceof BindException){
            BindException bindException = (BindException)e;
            analysisBindingResult(bindException.getBindingResult(),resultData);
        }else if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)e;
            analysisBindingResult(methodArgumentNotValidException.getBindingResult(),resultData);
        }else if (e instanceof AuthorizationException || e instanceof InsufficientAuthenticationException || e instanceof InvalidTokenException){
            resultData.setMessage("登陆异常，请重新登陆");
            resultData.setStatus(ResultDataState.LOGIN_FAIL.getKey());
        }
//        else if (e instanceof BlockException || e.getCause() instanceof BlockException) {
//            resultData.setMessage("sentinel block request(可能触发熔断/降级/限流……保护)");
//        }
        else {
            resultData.setMessage("程序开小差了！请与管理员联系！");
        }
        resultData.setExceptionMessage(e.getMessage());
    }

    private static void analysisBindingResult(final BindingResult bindingResult, final ResultData resultData){
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            resultData.setMessage(fieldError.getDefaultMessage());
            break;
        }
    }


}
