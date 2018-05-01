package com.example.response;

/**
 * This Interface represents the way of writing responses.
 * It has only a write method which should be implemented
 */
public interface ResponseWriter {

	/**
	 * This method presents how a response should be shown.
	 *
	 * @param response the response message that should be shown.
	 */
	void write(Response response);
}
