package br.uece.spring.api.util;

 // Importar Map e HashMap
 import java.util.HashMap;
 import java.util.Map;

 // Importar http, validation, bind, context e servlet
 import org.springframework.http.*;
 import org.springframework.validation.FieldError;
 import org.springframework.web.bind.*;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.context.request.WebRequest;
 import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 import br.uece.spring.api.domain.model.exception.*;

 @ControllerAdvice
 public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
          HttpHeaders headers, HttpStatusCode status, WebRequest request) {

          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
          });

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContaJaPagaException.class)
    public ResponseEntity<Object> handleContaJaPagaException(
      ContaJaPagaException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContaNaoExistenteException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
      ContaNaoExistenteException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContaJaExistenteException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
      ContaJaExistenteException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
      Exception exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}