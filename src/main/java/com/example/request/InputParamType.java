package com.example.request;

/**
 * Input Parameter types that can be used to shape input arguments.
 */
public enum InputParamType {
	CONFIG_PATH("-config.path"), PAGE_ID("-page.id"), ACCESS_TOKEN("-access.token"), ENABLE_FILTER("-enable.filter");
	private final String inputName;

	InputParamType(String inputName) {
		this.inputName = inputName;
	}

	public String getText() {
		return this.inputName;
	}
}
