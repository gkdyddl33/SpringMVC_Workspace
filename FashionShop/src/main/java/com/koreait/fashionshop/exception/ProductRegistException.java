package com.koreait.fashionshop.exception;

public class ProductRegistException extends RuntimeException{

	//  CRUD 작업 시 발생되는 예외
	public ProductRegistException(String msg) {
		super(msg);
	}
	
	public ProductRegistException(String msg,Throwable e) {
		super(msg,e);
	}
}
