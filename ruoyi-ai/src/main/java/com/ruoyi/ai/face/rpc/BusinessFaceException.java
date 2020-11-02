package com.ruoyi.ai.face.rpc;

/**
 * @author hanzhenyong
 * @create 2020-11-02 20:33
 */
public class BusinessFaceException extends RuntimeException {
    private ErrorCode errorCode;
    private String msg;
    private String msgCN;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgCN() {
        return msgCN;
    }

    public void setMsgCN(String msgCN) {
        this.msgCN = msgCN;
    }

    public BusinessFaceException(Response response) {
        this.errorCode = new ErrorCode() {
            @Override
            public Integer getCode() {
                return response.getCode();
            }

            @Override
            public String getDesc() {
                return response.getMsg();
            }

            @Override
            public String getDescCN() {
                return response.getMsg();
            }
        };
        this.msg=response.getMsg();
        this.msgCN=response.getMsg();
    }

    public BusinessFaceException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.msg= errorCode.getDesc();
        this.msgCN=errorCode.getDescCN();
    }

    public BusinessFaceException(ErrorCode errorCode, String msg) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.msg = msg;
        this.msgCN=msg;
    }

    public BusinessFaceException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.msg= errorCode.getDesc();
        this.msgCN=errorCode.getDescCN();
    }


    public BusinessFaceException(Throwable cause, ErrorCode errorCode, String msg) {
        super(cause);
        this.errorCode = errorCode;
        this.msg = msg;
        this.msgCN=msg;
    }
}
