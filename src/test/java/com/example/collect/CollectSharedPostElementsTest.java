package com.example.collect;

import com.example.collect.CollectSharedPostElements;
import com.example.util.BaseTest;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class CollectSharedPostElementsTest extends BaseTest {

	@Test public void TestSharedPostCountWithoutPatternCheck() {
		String postId = "1597890090324971_1598034373643876";

		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectSharedPostElements(postId, fbClient, false));
		Assert.assertEquals(3, ResultMap.getResultMapInstance().get(OutputParamType.SHARES.getText()).intValue());
		Assert.assertEquals(2, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestSharedPostCountWithPatternCheck() {
		String postId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectSharedPostElements(postId, fbClient, true));
		Assert.assertEquals(0, ResultMap.getResultMapInstance().get(OutputParamType.SHARES.getText()).intValue());
		Assert.assertEquals(0, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}
}
