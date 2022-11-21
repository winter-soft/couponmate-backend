package com.daou.kooteam.exception;

import com.daou.kooteam.dto.ExceptionDTO;
import com.daou.kooteam.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SwhackathonException.class)
    protected ResponseDTO<ExceptionDTO> swHackathonException(SwhackathonException e){
        log.info("SwhackathonException Running..");
        return new ResponseDTO<>(e.getHttpStatus().value(), new ExceptionDTO(e.getMessage()));
    }
}
