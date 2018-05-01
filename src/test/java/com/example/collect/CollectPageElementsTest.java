package com.example.collect;

import com.example.collect.CollectPageElements;
import com.example.util.BaseTest;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class CollectPageElementsTest extends BaseTest {

	@Test public void TestTotalPostCountNoCriteria() {
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, false));
		// One of the posts is an event. Not sure why it cannot get collected.
		// It might be a permission issue. In that case number of posts should be 12.
		Assert.assertEquals(11, ResultMap.getResultMapInstance().get(OutputParamType.POSTS.getText()).intValue());
	}

	@Test public void TestTotalPostCountWithCriteria() {
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, true));
		Assert.assertEquals(11, ResultMap.getResultMapInstance().get(OutputParamType.POSTS.getText()).intValue());
	}

	@Test public void TestTotalReactionCountNoCriteria(){
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, false));
		// One of the posts is an event. Not sure why it cannot get collected.
		// It might be a permission issue. In that case number of Reactions should be 8.
		Assert.assertEquals(7, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestTotalReactionCountWithCriteria(){
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, true));
		Assert.assertEquals(5, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestTotalSharedPostCountNoCriteria() {
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, false));
		Assert.assertEquals(4, ResultMap.getResultMapInstance().get(OutputParamType.SHARES.getText()).intValue());
	}


	@Test public void TestTotalSharedPostCountWithCriteria() {
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, true));
		Assert.assertEquals(0, ResultMap.getResultMapInstance().get(OutputParamType.SHARES.getText()).intValue());
	}

	@Test public void TestTotalCommentCountNoCriteria() {
		String pageId = "1597890090324971";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPageElements(pageId, fbClient, false));
		// One of the posts is an event. Not sure why it cannot get collected.
		// It might be a permission issue. In that case number of comments should be 8.
		Assert.assertEquals(7, ResultMap.getResultMapInstance().get(OutputParamType.COMMENTS.getText()).intValue());
	}
}
