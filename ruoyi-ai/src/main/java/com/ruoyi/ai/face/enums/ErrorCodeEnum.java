package com.ruoyi.ai.face.enums;


import com.ruoyi.ai.face.rpc.ErrorCode;

/**
 * @author hanzhenyong
 * @create 2020-11-02 20:18
 */
public enum ErrorCodeEnum implements ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0, "success", "成功"),
    FAIL(1, "fail", "失败"),
    PARAM_ERROR(2, "param error", "参数错误"),
    SYSTEM_ERROR(999, "system error", "系统错误"),

    ;
    private Integer code;
    private String desc;
    private String descCN;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    ErrorCodeEnum(Integer code, String desc, String descCN) {
        this.code = code;
        this.desc = desc;
        this.descCN = descCN;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getDescCN() {
        return descCN;
    }

}