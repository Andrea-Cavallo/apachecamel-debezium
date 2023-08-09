package com.producer.artemis.exception;

public class ArtemisSendException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7947625008841089662L;

	public ArtemisSendException(String message, Throwable cause) {
		super(message, cause);
	}

}
