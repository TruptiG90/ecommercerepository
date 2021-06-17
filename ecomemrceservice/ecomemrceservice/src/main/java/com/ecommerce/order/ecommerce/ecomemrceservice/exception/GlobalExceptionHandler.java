package com.ecommerce.order.ecommerce.ecomemrceservice.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
		
	@ExceptionHandler(DuplicateEntryException.class)
	public final ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException duplicateEntryException,
			
			WebRequest webRequest)
	{
		String  message = duplicateEntryException.getMessage();
		
		return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
		}
	
	

@ExceptionHandler(UserNotFoundException.class)
public final ResponseEntity<String> handleBookNotFoundException(UserNotFoundException bookNotFoundException,
		
		WebRequest webRequest)
{
	String  message = bookNotFoundException.getMessage();
	
	return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
	}

@ExceptionHandler(IncorrectAmountException.class)
public final ResponseEntity<String> handleIncorrectAmountException(IncorrectAmountException incorrectAmountException,
		
		WebRequest webRequest)
{
	String  message = incorrectAmountException.getMessage();
	
	return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
	}

@ExceptionHandler(IncorrectAccountException.class)
public final ResponseEntity<String> handleIncorrectAccountException(IncorrectAmountException incorrectAccountException,
		
		WebRequest webRequest)
{
	String  message = incorrectAccountException.getMessage();
	
	return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
	}

@ExceptionHandler(IncorrectUserNamePasswordException.class)
public final ResponseEntity<String> handleBookNotFoundException(IncorrectUserNamePasswordException incorrectUserNamePasswordException,
		
		WebRequest webRequest)
{
	String  message = incorrectUserNamePasswordException.getMessage();
	
	return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
	}

@ExceptionHandler(UnableToCreateUserRegistrationExeption.class)
public final ResponseEntity<String> handleUnableToCreateUserRegistrationExeption(UnableToCreateUserRegistrationExeption unableToCreateUserRegistrationExeption,
		
		WebRequest webRequest)
{
	String  message = unableToCreateUserRegistrationExeption.getMessage();
	
	return new ResponseEntity<> (message,HttpStatus.BAD_REQUEST);
	}



@ExceptionHandler
@ResponseStatus(HttpStatus.BAD_REQUEST)
ResponseEntity<Object> handleConstarintViolationException(ConstraintViolationException constraintViolationException,
		WebRequest webrequest){
	Arrays.asList(constraintViolationException).stream()
	.map(GlobalExceptionHandler::populate).collect(Collectors.toList());
	return new ResponseEntity<>("Invalid data provided "+ constraintViolationException.getMessage(),HttpStatus.BAD_REQUEST);
}

public static ExceptionResponse populate(ConstraintViolationException constraintViolationException) {
	
	ExceptionResponse exceptionResponse = new ExceptionResponse(constraintViolationException.getMessage(),HttpStatus.BAD_REQUEST);
	return exceptionResponse;
}





}