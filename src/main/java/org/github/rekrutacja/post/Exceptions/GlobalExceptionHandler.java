package org.github.rekrutacja.post.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(FileSaveException.class)
    public ResponseEntity<ErrorResponse> handleFileSaveException(FileSaveException ex) {
        logger.error("Obsługa wyjątku FileSaveException", ex);
        ErrorResponse errorResponse = new ErrorResponse(
            "Nie udało się zapisać plików",
            ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Obsługa nieoczekiwanego wyjątku", ex);
        ErrorResponse errorResponse = new ErrorResponse(
            "Wystąpił nieoczekiwany błąd",
            "Skontaktuj się z administratorem systemu"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Klasa pomocnicza do reprezentowania odpowiedzi błędu
    public static class ErrorResponse {
        private String message;
        private String details;
        
        public ErrorResponse(String message, String details) {
            this.message = message;
            this.details = details;
        }
        
        // Gettery i settery
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public String getDetails() {
            return details;
        }
        
        public void setDetails(String details) {
            this.details = details;
        }
    }
}