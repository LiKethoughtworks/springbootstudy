package com.entity.hello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyCustomerizedException extends RuntimeException {
    public MyCustomerizedException() {
    }

    public MyCustomerizedException(String message) {
        super(message);
        //如果使用controller advice，可以返回给客户一个更通用的message，我们只把现在的log下来
//        @RestControllerAdvice
//        public class ControllerAdvice {
//
//            private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
//
//            @ExceptionHandler({ServiceRequestException.class, HystrixRuntimeException.class})
//            @ResponseStatus(HttpStatus.BAD_REQUEST)
//            public ErrorResult handleServiceRequestException(Exception e) {
//                logger.error(e.getMessage(), e);
//                return new ErrorResult(SERVICE_REQUEST_ERROR);
//            }
    }
}