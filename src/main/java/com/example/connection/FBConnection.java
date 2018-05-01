package com.example.connection;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.util.Utility;

/**
 * This singleton class maintain connection to Facebook Graph API.
 * This class is thread-safe and makes sure only one instance of concoction is created.
 */

public class FBConnection {

	private static final Logger LOG = LoggerFactory.getLogger(FBConnection.class);
	private static final String DEFAULT_TOKEN_URL = "conf/AccessToken";
	private static FacebookClient fbClientInstance;

	private FBConnection() {
	}

	private static FacebookClient establishConnection(String accessToken) {
		LOG.debug("A connection to Facebook API is established!");
		return new DefaultFacebookClient(accessToken, Version.VERSION_2_12);
	}

	/**
	 * A method to get or create an instance of Facebook connection.
	 *
	 * @param accessToken the access token to be used to establish connection
	 *                    if it is not established yet.
	 * @return a FacebookClient to be used to collect from Facebook.
	 */

	public static synchronized FacebookClient getInstance(String accessToken) {
		if (fbClientInstance == null) {
			fbClientInstance = establishConnection(accessToken);
		}
		return fbClientInstance;
	}

	/**
	 * Get a default connection instance in a case that no access token is provided.
	 *
	 * @return a FacebookClient to be used to collect from Facebook.
	 */

	public static synchronized FacebookClient getDefaultInstance() {
		String defaultToken = Utility.readTokensFromFile(DEFAULT_TOKEN_URL).get(0);
		return getInstance(defaultToken);
	}
}
