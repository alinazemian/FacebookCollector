package com.example.request;

/**
 * {@inheritDoc}
 */
public class BasicRequest implements Request {

	private String sourceId;

	private boolean isFilterEnabled;

	@Override public boolean isFilterEnabled() {
		return isFilterEnabled;
	}

	public void setFilterEnabled(boolean filterEnabled) {
		isFilterEnabled = filterEnabled;
	}

	@Override public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

}
