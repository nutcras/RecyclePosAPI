package com.example.sellWebApp.dto.response;



import com.example.sellWebApp.dto.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.UUID;

@Slf4j
public class Response<T> extends ResponseEntity<ResponseApi<T>> {



    public Response(HttpStatus status) {
        super(status);
    }

    public Response(ResponseApi<T> body, HttpStatus status) {
        super(body, status);
    }




    public static <T> Response<T> unauthorized() {
        ResponseApi<T> responseApi = new ResponseApi<>(ResponseApi.Status.UNAUTHORIZED);
        return new Response<>(responseApi, HttpStatus.UNAUTHORIZED);
    }

    public static <T> Response<T> validationException() {
        ResponseApi<T> responseApi = new ResponseApi<>(ResponseApi.Status.VALIDATION_EXCEPTION);
        return new Response<>(responseApi, HttpStatus.NOT_ACCEPTABLE);
    }

    public static <T> Response<T> wrongCredentials() {
        ResponseApi<T> responseApi = new ResponseApi<>(ResponseApi.Status.WRONG_CREDENTIALS);
        return new Response<>(responseApi, HttpStatus.FORBIDDEN);
    }

    public static <T> Response<T> accessDenied() {
        ResponseApi<T> responseApi = new ResponseApi<>(ResponseApi.Status.ACCESS_DENIED);
        return new Response<>(responseApi, HttpStatus.METHOD_NOT_ALLOWED);
    }

    public static <T> Response<T> exception() {
        ResponseApi<T> responseApi = new ResponseApi<>(ResponseApi.Status.EXCEPTION);
        return new Response<>(responseApi, HttpStatus.EXPECTATION_FAILED);
    }

    public static Response<Void> buildNotFound() {
        ResponseApi<Void> responseApi = new ResponseApi<>(ResponseApi.Status.NOT_FOUND);
        return new Response<>(responseApi, HttpStatus.NOT_FOUND);
    }

    public static Response<Void> duplicateEntity() {
        ResponseApi<Void> responseApi = new ResponseApi<>(ResponseApi.Status.DUPLICATE_ENTITY);
        return new Response<>(responseApi, HttpStatus.BAD_REQUEST);
    }


    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        UUID id = UUID.randomUUID();
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(Calendar.getInstance().getTime())
                .setTrackId(id);
        ResponseApi<T> body = this.getBody();
        if (body != null) {
            body.setErrors(error);
        }
        log.error("Log trackid : {}", id, ex);
    }

    public void addShortErrorMsgToResponse(String errorMsg, Exception ex) {
        UUID id = UUID.randomUUID();
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(errorMsg);
        ResponseApi<T> body = this.getBody();
        if (body != null) {
            body.setErrors(error);
        }
        log.error("Log trackid : {}", id, ex);
    }
}

