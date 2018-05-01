package com.example.request;

import com.example.collect.CollectPageElements;
import com.restfb.FacebookClient;

import java.util.concurrent.ForkJoinPool;

/**
 * {@inheritDoc}
 */
public class BasicRequestHandler implements RequestHandler {
	@Override public void handleRequest(Request request, FacebookClient fbClient) {
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(request.getSourceId(), fbClient, request.isFilterEnabled()));
	}
}
