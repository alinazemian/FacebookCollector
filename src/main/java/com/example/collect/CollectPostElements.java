package com.example.collect;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * A sub-class of {@link RecursiveAction} to collect Post elements recursively.
 * This class collect posts and any comments or comments of comments recursively.
 */

public class CollectPostElements extends RecursiveAction {
	private final String sourceId;
	private final transient FacebookClient fbClient;
	private final boolean isFilterEnabled;

	public CollectPostElements(String sourceId, FacebookClient fbClient, boolean isFilterEnabled) {
		this.sourceId = sourceId;
		this.fbClient = fbClient;
		this.isFilterEnabled = isFilterEnabled;
	}

	@Override protected void compute() {
		Connection<Comment> commentPages = fbClient.fetchConnection(sourceId + "/comments", Comment.class,
				Parameter.with("fields", "message,reactions.summary(true)"));
		List<RecursiveAction> tasks = new ArrayList<>();
		long reactionCount = 0L;
		long commentCount = 0L;
		for (List<Comment> comments : commentPages) {
			for (Comment comment : comments) {
				commentCount++;
				if (comment.getReactions().getTotalCount() > 0)
					reactionCount += comment.getReactions().getTotalCount();
				CollectPostElements collectCommentElements = new CollectPostElements(comment.getId(), fbClient,
						isFilterEnabled);
				tasks.add(collectCommentElements);

			}
		}
		if (!tasks.isEmpty())
			ForkJoinTask.invokeAll(tasks);
		ResultMap.incrementValue(OutputParamType.REACTIONS, reactionCount);
		ResultMap.incrementValue(OutputParamType.COMMENTS, commentCount);
	}

}
