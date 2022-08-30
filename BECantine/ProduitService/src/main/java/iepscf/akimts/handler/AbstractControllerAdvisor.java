package iepscf.akimts.handler;


import iepscf.akimts.handler.BasicControllerExceptionHandler;
import iepscf.akimts.handler.annotation.AdvisorHandled;
import iepscf.akimts.handler.annotation.BadRequestHandler;
import iepscf.akimts.handler.models.ErrorDTO;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractControllerAdvisor extends ResponseEntityExceptionHandler {
    private final Set<BasicControllerExceptionHandler> handlings = new HashSet<>();

    public AbstractControllerAdvisor() {
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ErrorDTO> handle(Throwable ex) {
        ResponseEntity<ErrorDTO> responseEntity;
        return (responseEntity = this.handleAdviserHandler(ex)) == null
                && (responseEntity = this.handleBadRequestHandler(ex)) == null
                && (responseEntity = this.handleHandlings(ex)) == null ?
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO("une erreur inconnue s'est produite"))
                : responseEntity;
    }

    public void addHandling(BasicControllerExceptionHandler handling) {
        this.handlings.add(handling);
    }

    private ResponseEntity<ErrorDTO> handleAdviserHandler(Throwable ex) {
        AdvisorHandled adviserHandled = (AdvisorHandled) AnnotationUtils.findAnnotation(ex.getClass(), AdvisorHandled.class);
        return adviserHandled != null ? ResponseEntity.status(adviserHandled.value()).body(ErrorDTO.of(ex)) : null;
    }

    private ResponseEntity<ErrorDTO> handleBadRequestHandler(Throwable ex) {
        BadRequestHandler badRequestHandler = (BadRequestHandler)AnnotationUtils.findAnnotation(this.getClass(), BadRequestHandler.class);
        return badRequestHandler != null ? Arrays.stream(badRequestHandler.value()).filter((exClazz) -> {
            return exClazz.isAssignableFrom(ex.getClass());
        }).findFirst().map((exClazz) -> {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO.of(ex));
        }).orElse(null) : null;
    }

    private ResponseEntity<ErrorDTO> handleHandlings(Throwable ex) {
        return this.handlings.stream()
                .filter((handling) -> handling.getExceptionClazz().isAssignableFrom(ex.getClass()))
                .findFirst()
                .map(h -> new ResponseEntity<>(ErrorDTO.of(ex),h.getStatus()))
                .orElse(null);
    }
}