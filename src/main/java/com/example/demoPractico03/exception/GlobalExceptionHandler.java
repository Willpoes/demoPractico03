package com.example.demoPractico03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

//vas a funcionar con un tipo de falla
//en su interior contiene errores implementados como metodos,
@ControllerAdvice
public class GlobalExceptionHandler {
    //metodo -> manejador!
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handlerBadRequest(BadRequestException badRequestException){
        Map<String, Object> body = new LinkedHashMap<>();
        //lo q quiero
        //body.put("timestamp", LocalDateTime.new().toLocaleDate().toString());
        body.put("timestamp", LocalDateTime.now().toLocalDate().toString());
        //valor de l error
        body.put("status", HttpStatus.BAD_REQUEST.value());
        //mensaje
        body.put("error", "Mala peticion");
        body.put("message", badRequestException.getMessage());

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);

    }
}
