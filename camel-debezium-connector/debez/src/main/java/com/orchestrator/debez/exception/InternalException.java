package com.orchestrator.debez.exception;

public class InternalException extends AbstractException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3264111174581166479L;

	public InternalException() {
	}

	public InternalException(String message) {
		super(message);
	}

	public InternalException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
