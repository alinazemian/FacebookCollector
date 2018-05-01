package com.example.request;

import com.restfb.FacebookClient;

/**
 * This Interface represents the way of processing a Request.
 * It has only a handleRequest method which should be implemented.
 */

public interface RequestHandler {

	/**
	 * Handle a request by calculating a response
	 * @param request a Request to be handled
	 * @param fbClient FacebookClient to be used for handling a request.
	 */

	void handleRequest(Request request, FacebookClient fbClient);
}
