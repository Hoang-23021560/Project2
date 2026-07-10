package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customexception.FieldRequierdException;
import com.javaweb.model.ErrorResponseDTO;
// Trả về trạng thái http status code: hãy trả về mã trạng thái có ý nghĩa mô tả chính xác loại lỗi đó.
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	//@ExceptionHandler mô tả xem là với cái lỗi nào thì hàm tương ứng là hàm nào
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {
			
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
			errorResponseDTO.setError(ex.getMessage());
			List<String> details = new ArrayList<>();
			details.add("So nguyen lam sao chia het cho 0 duoc");
			errorResponseDTO.setDetail(details);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "City not found");
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(FieldRequierdException.class)
    public ResponseEntity<Object> handleFieldRequierdException(
    		FieldRequierdException ex, WebRequest request) {
			
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
			errorResponseDTO.setError(ex.getMessage());
			List<String> details = new ArrayList<>();
			details.add("Check lai nam hoac numberOf co the bi null do");
			errorResponseDTO.setDetail(details);
			return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
	}
//
//    @ExceptionHandler(NoDataFoundException.class)
//    public ResponseEntity<Object> handleNodataFoundException(
//            NoDataFoundException ex, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "No cities found");
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers,
//            HttpStatusCode status, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDate.now());
//        body.put("status", status.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

}

