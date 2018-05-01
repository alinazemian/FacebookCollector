package com.example.request;

import java.util.EnumMap;
import java.util.Map;

/**
 * A concrete class to maintain all input parameters and related constants.
 */

public class InputParams {
	public static final String DEFAULT_VALUE = "default";
	public static final String FALSE_VALUE = "false";
	public static final String TRUE_VALUE = "true";

	private Map<InputParamType, String> paramMap = new EnumMap<>(InputParamType.class);

	public Map<InputParamType, String> getParams() {
		return paramMap;
	}

	public void setParams(Map<InputParamType, String> inputParams) {
		paramMap = inputParams;
	}
}
