package com.example;

import com.example.connection.FBConnection;
import com.example.request.*;
import com.example.response.BasicResponse;
import com.example.response.ConsoleResponseWriter;
import com.example.response.Response;
import com.example.response.ResponseWriter;
import com.example.util.Utility;
import com.restfb.FacebookClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	/**
	 * Start the application with a property argument to set a config directory to load
	 * log4j.properties. If no argument provided, log4j may not work properly.
	 *
	 * @param args Ex. -config.path=../conf -access.token=xyz -page.id=123 -enable.filter=true
	 */
	public static void main(String[] args) {
		try {
			FacebookClient fbClient;
			InputParams inputParams = Utility.parseArguments(args);
			Map<InputParamType, String> paramMap = inputParams.getParams();
			Utility.loadLogConfigFile(paramMap.get(InputParamType.CONFIG_PATH));
			if (InputParams.DEFAULT_VALUE.equals(paramMap.get(InputParamType.ACCESS_TOKEN)))
				fbClient = FBConnection.getDefaultInstance();
			else {
				fbClient = FBConnection.getInstance(paramMap.get(InputParamType.ACCESS_TOKEN));
			}
			LOG.info("Waiting for the result...");
			RequestHandler requestHandler = new BasicRequestHandler();
			Request request = new BasicRequest();
			request.setSourceId(paramMap.get(InputParamType.PAGE_ID));
			request.setFilterEnabled(Boolean.parseBoolean(paramMap.get(InputParamType.ENABLE_FILTER)));
			requestHandler.handleRequest(request, fbClient);
			Response response = new BasicResponse();
			ResponseWriter writer = new ConsoleResponseWriter();
			writer.write(response);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
}
