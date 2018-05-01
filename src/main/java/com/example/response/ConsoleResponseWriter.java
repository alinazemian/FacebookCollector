package com.example.response;

import java.util.Map;

/**
 * {@inheritDoc}
 */
public class ConsoleResponseWriter implements ResponseWriter {
	@Override public void write(Response response) {
		Map resultMap = response.getResponse();
		System.out.println(
				OutputParamType.COMMENTS.getText() + " : " + resultMap.get(OutputParamType.COMMENTS.getText()));
		System.out.println(OutputParamType.SHARES.getText() + " : " + resultMap.get(OutputParamType.SHARES.getText()));
		System.out.println(OutputParamType.POSTS.getText() + " : " + resultMap.get(OutputParamType.POSTS.getText()));
		System.out.println(
				OutputParamType.REACTIONS.getText() + " : " + resultMap.get(OutputParamType.REACTIONS.getText()));
	}
}
