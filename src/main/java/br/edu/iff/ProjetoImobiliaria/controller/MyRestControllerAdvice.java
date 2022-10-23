package br.edu.iff.ProjetoImobiliaria.controller;

import br.edu.iff.ProjetoImobiliaria.exception.Error;
import br.edu.iff.ProjetoImobiliaria.exception.NotFoundException;
import br.edu.iff.ProjetoImobiliaria.exception.PropertyError;
import br.edu.iff.ProjetoImobiliaria.exception.ValidationError;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultError(Exception e, HttpServletRequest request) {
        Error ex = new Error(
                Calendar.getInstance(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity defaultError(NotFoundException e, HttpServletRequest request) {
        Error ex = new Error(
                Calendar.getInstance(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity validationError(ConstraintViolationException e, HttpServletRequest request) {
        ValidationError ex = new ValidationError(
                Calendar.getInstance(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação.",
                request.getRequestURI()
        );
        for (ConstraintViolation cv : e.getConstraintViolations()) {
            PropertyError pe = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
            ex.getErrors().add(pe);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity defaultError(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError ex = new ValidationError(
                Calendar.getInstance(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação.",
                request.getRequestURI()
        );
        for (FieldError fe : e.getBindingResult().getFieldErrors()) {
            PropertyError pe = new PropertyError(fe.getField(), fe.getDefaultMessage());
            ex.getErrors().add(pe);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex);
    }
}
