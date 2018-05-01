package com.example.collect;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.example.response.OutputParamType;
import com.example.response.ResultMap;
import com.example.util.Utility;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * A sub-class of {@link RecursiveAction} to collect Shared Post elements recursively.
 */

public class CollectSharedPostElements extends RecursiveAction {
	private final String postId;
	private final transient FacebookClient fbClient;
	private final boolean isFilterEnabled;

	public CollectSharedPostElements(String postId, FacebookClient fbClient, boolean isFilterEnabled) {
		this.postId = postId;
		this.fbClient = fbClient;
		this.isFilterEnabled = isFilterEnabled;
	}


	@Override protected void compute() {
		Connection<Post> postPages = fbClient.fetchConnection(postId + "/sharedposts", Post.class,
				Parameter.with("fields", "message,reactions.summary(true)"));

		for (List<Post> posts : postPages) {
			posts.stream().filter(post -> Utility.matchSelectionPattern(post.getMessage(), isFilterEnabled))
					.forEach(post -> {
						ResultMap.incrementValue(OutputParamType.REACTIONS, post.getReactions().getTotalCount());
						ResultMap.incrementValue(OutputParamType.SHARES, 1);
					});
		}
	}

}
