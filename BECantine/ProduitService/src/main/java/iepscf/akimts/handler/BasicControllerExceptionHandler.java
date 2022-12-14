package iepscf.akimts.handler;

import org.springframework.http.HttpStatus;

public class BasicControllerExceptionHandler {
    private final Class<? extends Throwable> exceptionClazz;
    private final HttpStatus status;

    private BasicControllerExceptionHandler(Class<? extends Throwable> clazz, HttpStatus status) {
        this.exceptionClazz = clazz;
        this.status = status;
    }

    public Class<? extends Throwable> getExceptionClazz() {
        return this.exceptionClazz;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public static <S extends Throwable> BasicControllerExceptionHandler.BasicControllerExceptionHandlerBuilder<S> forException(Class<S> exceptionClazz) {
        return new BasicControllerExceptionHandler.BasicControllerExceptionHandlerBuilder<>(exceptionClazz);
    }

    public static class BasicControllerExceptionHandlerBuilder<S extends Throwable> {
        private final Class<? extends S> clazz;

        private BasicControllerExceptionHandlerBuilder(Class<? extends S> clazz) {
            this.clazz = clazz;
        }

        public BasicControllerExceptionHandler status(HttpStatus status) {
            return new BasicControllerExceptionHandler(this.clazz, status);
        }

        public BasicControllerExceptionHandler badRequest() {
            return new BasicControllerExceptionHandler(this.clazz, HttpStatus.BAD_REQUEST);
        }
    }
}
