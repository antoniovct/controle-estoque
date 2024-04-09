package br.com.antonio_victor.controle_estoque.error;

import br.com.antonio_victor.controle_estoque.exceptions.InsufficientQuantityException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TrataErro {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {

        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        var errosDto = erros.stream().map(ErrosDto::new).toList();
        return ResponseEntity.badRequest().body(errosDto);
    }
    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity tratarQuantidadeInsuficiente(InsufficientQuantityException ex) {
        var erro = ex.getMessage();
        return ResponseEntity.badRequest().body(erro);
    }
    public record ErrosDto(String campo, String mensagem) {
        public ErrosDto(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
