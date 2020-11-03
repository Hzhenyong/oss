package com.ruoyi.ai.face.rpc;

import com.ruoyi.ai.face.enums.ErrorCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author hanzhenyong
 * @create 2020-11-02 20:35
 */
public class GlobalFaceExceptionHandler {


    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessFaceException.class)
    public Response businessException(BusinessFaceException e) {
        //log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(e.getErrorCode().getCode());
        response.setMsg(e.getMsgCN());
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleIllegalArgumentException(IllegalArgumentException e) {
        //log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(ErrorCodeEnum.PARAM_ERROR.getCode());
        response.setMsg(e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        //log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        response.setMsg(ErrorCodeEnum.SYSTEM_ERROR.getDescCN());
        return response;
    }


}
