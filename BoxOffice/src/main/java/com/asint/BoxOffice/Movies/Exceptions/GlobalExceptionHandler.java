package com.asint.BoxOffice.Movies.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> runtimeExceptionHandler(RuntimeException me, WebRequest req)  {

        ErrorDetails err=new ErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genericExceptionHandler(Exception me, WebRequest req)  {


        ErrorDetails err=new ErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException me, WebRequest req)  {


        ErrorDetails err=new ErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }
	  
}
