package com.example.collect;

import com.example.collect.CollectPostElements;
import com.example.util.BaseTest;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class CollectPostElementsTest extends BaseTest {

	@Test public void TestTotalPostReactionCountNoCriteriaّ() {
		String postId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(postId, fbClient, false));
		Assert.assertEquals(4, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestTotalPostReactionCountWithCriteria() {
		String postId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(postId, fbClient, true));
		Assert.assertEquals(4, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestTotalPostCommentCount() {
		String postId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(postId, fbClient, false));
		Assert.assertEquals(7, ResultMap.getResultMapInstance().get(OutputParamType.COMMENTS.getText()).intValue());
	}

	@Test public void TestLastLevelCommentCommentCountNoCriteria() {
		String commentId = "1598034373643876_1598036400310340";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(commentId, fbClient, false));
		Assert.assertEquals(6, ResultMap.getResultMapInstance().get(OutputParamType.COMMENTS.getText()).intValue());
	}

	@Test public void TestLastLevelCommentReactionCountNoCriteria() {
		String commentId = "1598034373643876_1598036400310340";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(commentId, fbClient, false));
		Assert.assertEquals(3, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}

	@Test public void TestNestedCommentCountNoCriteria() {
		String commentId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(commentId, fbClient, false));
		Assert.assertEquals(7, ResultMap.getResultMapInstance().get(OutputParamType.COMMENTS.getText()).intValue());
	}

	@Test public void TestNestedCommentReactionCountNoCriteria() {
		String commentId = "1597890090324971_1598034373643876";
		ForkJoinPool threadPool = ForkJoinPool.commonPool();
		threadPool.invoke(new CollectPostElements(commentId, fbClient, false));
		Assert.assertEquals(4, ResultMap.getResultMapInstance().get(OutputParamType.REACTIONS.getText()).intValue());
	}
}
