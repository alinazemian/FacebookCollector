package com.example.request;

/**
 * This Interface represents different requests.
 * It has multiple setter and getters that should be implemented.
 */

public interface Request {
	String getSourceId();
	boolean isFilterEnabled();
	void setFilterEnabled(boolean isFilterEnabled);
	void setSourceId(String sourceId);
}
