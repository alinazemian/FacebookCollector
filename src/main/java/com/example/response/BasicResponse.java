package com.example.response;

import java.util.Map;

/**
 * {@inheritDoc}
 */
public class BasicResponse implements Response {
	@Override public Map getResponse() {
		return ResultMap.getResultMapInstance();
	}
}
