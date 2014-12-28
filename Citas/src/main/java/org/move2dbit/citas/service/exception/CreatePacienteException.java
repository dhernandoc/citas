package org.move2dbit.citas.service.exception;

public class CreatePacienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreatePacienteException(String message,Throwable th)
	{
		super(message, th);
	}
}
