package com.example.connection;

import com.restfb.types.Page;
import com.example.util.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class FBConnectionTest extends BaseTest {

	@Test public void testConnection() {
		Page page = fbClient.fetchObject("1597890090324971", Page.class);
		Assert.assertEquals("Raj/Anton's Page", page.getName());
	}
}
