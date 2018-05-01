package com.example.response;

/**
 * Output types that can be used to shape application output.
 */
public enum OutputParamType {
	SHARES("shares"), COMMENTS("comments"), REACTIONS("reactions"), POSTS("posts");
	private final String outputName;

	OutputParamType(String outputName) {
		this.outputName = outputName;
	}

	public String getText() {
		return this.outputName;
	}
}
