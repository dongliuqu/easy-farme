package com.lihao.core.exception;

import com.lihao.common.entity.vo.Result;
import com.lihao.common.exception.SystemErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;


@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        return Result.fail(String.valueOf(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result serviceException(MethodArgumentNotValidException ex) {
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    //@ExceptionHandler(value = {DuplicateKeyException.class})
    //public Result duplicateKeyException(DuplicateKeyException ex) {
    //    return Result.fail(SystemErrorType.DUPLICATE_PRIMARY_KEY);
    //}


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Result duplicateKeyException(IllegalArgumentException ex) {
        return Result.fail(ex.getMessage());
    }


    @ExceptionHandler(value = {IllegalStateException.class})
    public Result illegalStateException(IllegalStateException ex) {
        return Result.fail(ex.getMessage());
    }

    //@ExceptionHandler(value = {ConstraintViolationException.class})
    //public Result constraintViolationException(ConstraintViolationException ex) {
    //    Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
    //    List<String> collect = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    //    return Result.fail(collect.toString());
    //}

    @ExceptionHandler(value = {RuntimeException.class})
    public final Result runTimeException(RuntimeException ex) {
        return Result.fail(SystemErrorType.SYSTEM_ERROR.getMesg());
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Result exception(Exception ex) {
        return Result.fail(SystemErrorType.SYSTEM_ERROR.getMesg());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Result throwable() {
        return Result.fail(SystemErrorType.SYSTEM_ERROR);
    }


}