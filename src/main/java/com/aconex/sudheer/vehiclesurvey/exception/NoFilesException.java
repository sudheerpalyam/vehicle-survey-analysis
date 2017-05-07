package com.aconex.sudheer.vehiclesurvey.exception;


/**
 * @author Sudheer Palyam
 * 06/05/2015
 * 
 * Exception - No data files available to process
 */
public class NoFilesException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoFilesException(String message) {
        super(message);
    }

}