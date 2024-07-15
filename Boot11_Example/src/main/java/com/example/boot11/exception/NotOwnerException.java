package com.example.boot11.exception;


public class NotOwnerException extends RuntimeException{
	//생성자
	public NotOwnerException(String message) {
		super(message);
	}
}
