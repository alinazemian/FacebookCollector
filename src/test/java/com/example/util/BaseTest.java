package com.example.util;

import com.example.util.Utility;
import com.restfb.FacebookClient;
import com.example.connection.FBConnection;
import org.junit.Before;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;

public class BaseTest {
	public static FacebookClient fbClient;


	@Before public void init() throws Exception {
		fbClient = FBConnection.getInstance(Utility.readTokensFromFile("src/test/resources/AccessToken").get(0));
	}

	// Reset Result Map instance before every test.
	@Before public void resetResultMap() {
		ResultMap.getResultMapInstance().put(OutputParamType.COMMENTS.getText(), 0L);
		ResultMap.getResultMapInstance().put(OutputParamType.SHARES.getText(), 0L);
		ResultMap.getResultMapInstance().put(OutputParamType.REACTIONS.getText(), 0L);
		ResultMap.getResultMapInstance().put(OutputParamType.POSTS.getText(), 0L);
	}
}
