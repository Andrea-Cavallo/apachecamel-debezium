package com.orchestrator.debez.exception;

public abstract class AbstractException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8768539222432925216L;

	protected AbstractException() {
	}

	protected AbstractException(String message) {
		super(message);
	}

	protected AbstractException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
