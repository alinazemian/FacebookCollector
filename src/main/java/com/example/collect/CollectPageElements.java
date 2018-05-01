package com.example.collect;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * A sub-class of {@link RecursiveAction} to collect Shared Page elements recursively.
 */

public class CollectPageElements extends RecursiveAction {
	private final String pageId;
	private final transient FacebookClient fbClient;
	private final boolean isFilterEnabled;

	public CollectPageElements(String pageId, FacebookClient fbClient, boolean isFilterEnabled) {
		this.pageId = pageId;
		this.fbClient = fbClient;
		this.isFilterEnabled = isFilterEnabled;
	}

	@Override protected void compute() {
		Connection<Post> postPages = fbClient.fetchConnection(pageId + "/posts", Post.class,
				Parameter.with("fields", "message,reactions.summary(true)"));
		long postCount = 0L;
		long reactionCount = 0L;
		// list of all recursive tasks
		List<RecursiveAction> tasks = new ArrayList<>();

		for (List<Post> posts : postPages) {
			for (Post post : posts) {

				postCount++;
				if (post.getReactions().getTotalCount() > 0)
					reactionCount += post.getReactions().getTotalCount();

				CollectPostElements collectPostElements = new CollectPostElements(post.getId(), fbClient,
						isFilterEnabled);
				CollectSharedPostElements collectSharedPostElements = new CollectSharedPostElements(post.getId(),
						fbClient, isFilterEnabled);
				tasks.add(collectPostElements);
				tasks.add(collectSharedPostElements);

			}
		}
		if (!tasks.isEmpty())
			ForkJoinTask.invokeAll(tasks);
		ResultMap.incrementValue(OutputParamType.REACTIONS, reactionCount);
		ResultMap.incrementValue(OutputParamType.POSTS, postCount);
	}

}
