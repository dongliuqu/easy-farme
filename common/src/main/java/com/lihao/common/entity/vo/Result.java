package com.lihao.common.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lihao.common.exception.ErrorType;
import com.lihao.common.exception.SystemErrorType;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    private String code;
    private String msg;
    private Instant time;
    private T data;

    public Result(ErrorType errorType, T data) {
        this.code = errorType.getCode();
        this.msg = errorType.getMesg();
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public T getData() {
        return data;
    }

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }


    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    private Result(String code, String mesg, T data) {
        this.code = code;
        this.msg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    public static <T> Result<T> alert(String msg) {
        return new Result<T>(SUCCESSFUL_CODE, msg, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static <T> Result<T> fail(ErrorType errorType, T data) {
        return new Result<T>(errorType, data);
    }

    public static <T> Result<T> fail(ErrorType errorType) {
        return new Result<T>(errorType, null);
    }

    /**
     * 系统异常类并返回异常消息
     *
     * @param msg
     * @return Result
     */
    public static Result fail(String msg) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR.getCode(), msg, null);
    }


    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    public boolean isFail() {
        return !isSuccess();
    }
}
