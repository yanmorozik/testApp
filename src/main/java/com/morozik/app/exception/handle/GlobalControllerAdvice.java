package com.morozik.app.exception.handle;

import com.morozik.app.dto.ResponseDto;
import com.morozik.app.exception.BadRequestException;
import com.morozik.app.exception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseDto handleBadRequestException(BadRequestException badRequestException) {
        return ResponseDto.builder()
                .code(badRequestException.getCode())
                .description(badRequestException.getMessage())
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseDto handleNotFoundException(NotFoundException notFoundException) {
        return ResponseDto.builder()
                .code(notFoundException.getCode())
                .description(notFoundException.getMessage())
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(Exception exception) {
        return ResponseDto.builder()
                .code(500)
                .description(exception.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                ResponseDto.builder()
                        .code(400)
                        .description(errors.toString())
                        .build(),
                BAD_REQUEST
        );
    }
}
