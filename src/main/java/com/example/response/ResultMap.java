package com.example.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A singleton class to represent a static Map to be used for maintaining results.
 */
public class ResultMap {
	private static final Logger LOG = LoggerFactory.getLogger(ResultMap.class);
	private static Map<String, Long> outputMap;

	private ResultMap() {
	}

	public static synchronized Map<String, Long> getResultMapInstance() {
		if (outputMap == null)
			outputMap = initializeResultMap();

		return outputMap;
	}

	private static Map<String, Long> initializeResultMap() {
		LOG.debug("A result amp is initiated!");
		outputMap = new ConcurrentHashMap<>();
		outputMap.put(OutputParamType.REACTIONS.getText(), 0L);
		outputMap.put(OutputParamType.SHARES.getText(), 0L);
		outputMap.put(OutputParamType.COMMENTS.getText(), 0L);
		outputMap.put(OutputParamType.POSTS.getText(), 0L);
		return outputMap;
	}

	/**
	 * A static and thread-safe method to be used for incrementing a value of the Result Map.
	 *
	 * @param key       the key of entry to be updated
	 * @param increment the value to be added to the current value of the corresponding entry
	 */
	public static synchronized void incrementValue(OutputParamType key, long increment) {
		LOG.debug("Result Map value for %s is incremented by %d!", key.getText(), increment);
		getResultMapInstance().put(key.getText(), increment + getResultMapInstance().get(key.getText()));
	}
}
