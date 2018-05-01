package com.example.util;

import com.example.exception.IllegalArgumentException;
import com.example.exception.InsufficientArgumentException;
import com.example.request.InputParamType;
import com.example.request.InputParams;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This class represents static utilities.
 */
public class Utility {
	private static final Logger LOG = LoggerFactory.getLogger(Utility.class);
	private static final Pattern selectionPattern = Pattern.compile("^.*(\\n|\\r|\\r\\n)$", Pattern.DOTALL);

	private Utility() {
	}

	/**
	 * A static method for checking if story message matches the required pattern.
	 *
	 * @return Returns true if it matches pattern, else returns false.
	 */
	public static boolean matchSelectionPattern(String story, boolean isCriteriaEnabled) {
		if (!isCriteriaEnabled)
			return true;
		else {
			String msg = story;
			if (msg != null && selectionPattern.matcher(msg).matches())
				return true;
		}
		return false;
	}

	/**
	 * A static method for reading tokens from a file.
	 * Tokens should be separated by lines.
	 *
	 * @param fileURL the URL of file to be used for access tokens.
	 * @return Returns the list of all access tokens
	 */
	public static List<String> readTokensFromFile(String fileURL) {
		List<String> inputTokens = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileURL)))) {

			String line;
			while ((line = br.readLine()) != null) {
				inputTokens.add(line);
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return inputTokens;
	}

	/**
	 * A static method for loading log4j.properties config file.
	 *
	 * @param configDir the path of directory for loading log4j.properties file.
	 */

	public static void loadLogConfigFile(String configDir) {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(configDir + File.separator + "log4j" + ".properties"));
			PropertyConfigurator.configure(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A static method for parsing input arguments.
	 *
	 * @param args args Ex. -config.path=../conf -access.token=xyz -page.id=123 -enable.filter=true
	 */

	public static InputParams parseArguments(String[] args) {
		InputParams inputParams = new InputParams();

		Map<InputParamType, String> paramMap = new EnumMap<>(InputParamType.class);
		if (args == null || args.length < 2)
			throw new InsufficientArgumentException(
					"At least two arguments are required\n  Ex. -config.path=../conf -page.id=123\"");

		if (args.length < 3) {
			if (contains(args, InputParamType.CONFIG_PATH.getText()) && contains(args,
					InputParamType.PAGE_ID.getText())) {
				if (!contains(args, InputParamType.ACCESS_TOKEN.getText())) {
					LOG.warn(
							"No -access.token parameter is provided. Default access token will be used for this application.");
					paramMap.put(InputParamType.ACCESS_TOKEN, InputParams.DEFAULT_VALUE);
				}
				if (!contains(args, InputParamType.ENABLE_FILTER.getText())) {
					LOG.warn(
							"No -enable.filter parameter is provided. Default value of 'false' will be used for this application.");
					paramMap.put(InputParamType.ENABLE_FILTER, InputParams.FALSE_VALUE);
				}
				paramMap.put(InputParamType.CONFIG_PATH, getParameter(args, InputParamType.CONFIG_PATH.getText()));
				paramMap.put(InputParamType.PAGE_ID, getParameter(args, InputParamType.PAGE_ID.getText()));
			} else
				throw new IllegalArgumentException(
						"-config.path and -page.id are required\n Ex. -config.path=../conf -page.id=123");
		} else if (args.length == 3) {
			if (contains(args, InputParamType.CONFIG_PATH.getText()) && contains(args,
					InputParamType.PAGE_ID.getText())) {
				if (!contains(args, InputParamType.ACCESS_TOKEN.getText())) {
					LOG.warn(
							"No -access.token parameter is provided. Default access token will be used for this application.");
					paramMap.put(InputParamType.ACCESS_TOKEN, InputParams.DEFAULT_VALUE);
				} else
					paramMap.put(InputParamType.ACCESS_TOKEN,
							getParameter(args, InputParamType.ACCESS_TOKEN.getText()));

				if (!contains(args, InputParamType.ENABLE_FILTER.getText())) {
					LOG.warn(
							"No -enable.filter parameter is provided. Default value of 'false' will be used for this application.");
					paramMap.put(InputParamType.ENABLE_FILTER, InputParams.FALSE_VALUE);
				} else {
					if (!InputParams.FALSE_VALUE.equals(getParameter(args, InputParamType.ENABLE_FILTER.getText()))
							&& !InputParams.TRUE_VALUE
							.equals(getParameter(args, InputParamType.ENABLE_FILTER.getText())))
						throw new IllegalArgumentException(
								"For -enable.filter only 'true' or 'false' value is acceptable!");
					paramMap.put(InputParamType.ENABLE_FILTER,
							getParameter(args, InputParamType.ENABLE_FILTER.getText()));
				}
				paramMap.put(InputParamType.CONFIG_PATH, getParameter(args, InputParamType.CONFIG_PATH.getText()));
				paramMap.put(InputParamType.PAGE_ID, getParameter(args, InputParamType.PAGE_ID.getText()));
			} else
				throw new IllegalArgumentException(
						"-config.path and -page.id are required\n Ex. -config.path=../conf -page.id=123");
		} else {
			if (contains(args, InputParamType.CONFIG_PATH.getText()) && contains(args, InputParamType.PAGE_ID.getText())
					&& contains(args, InputParamType.ACCESS_TOKEN.getText()) && contains(args,
					InputParamType.ENABLE_FILTER.getText())) {
				paramMap.put(InputParamType.ACCESS_TOKEN, getParameter(args, InputParamType.ACCESS_TOKEN.getText()));
				paramMap.put(InputParamType.CONFIG_PATH, getParameter(args, InputParamType.CONFIG_PATH.getText()));
				paramMap.put(InputParamType.PAGE_ID, getParameter(args, InputParamType.PAGE_ID.getText()));
				if (!InputParams.FALSE_VALUE.equals(getParameter(args, InputParamType.ENABLE_FILTER.getText()))
						&& !InputParams.TRUE_VALUE.equals(getParameter(args, InputParamType.ENABLE_FILTER.getText())))
					throw new IllegalArgumentException(
							"For -enable.filter only 'true' or 'false' value is acceptable!");
				paramMap.put(InputParamType.ENABLE_FILTER, getParameter(args, InputParamType.ENABLE_FILTER.getText()));
			} else
				throw new IllegalArgumentException(
						"-config.path and -page.id are required\n Ex. -config.path=../conf -page.id=123");
		}
		inputParams.setParams(paramMap);
		return inputParams;
	}

	private static String getParameter(String[] args, String value) {
		if (!contains(args, value))
			return "";
		else {
			for (String arg : args) {
				if (arg.startsWith(value))
					return arg.split("=")[1];
			}
			return "";
		}
	}

	private static boolean contains(String[] args, String value) {
		for (String arg : args) {
			if (arg.startsWith(value))
				return true;
		}
		return false;
	}
}
