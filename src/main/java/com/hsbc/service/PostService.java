package com.hsbc.service;

import com.hsbc.model.entity.Post;
import com.hsbc.model.dto.Like;
import com.hsbc.model.dto.UserPost;
import com.hsbc.model.dto.UserRepost;

import java.util.List;

public interface PostService {

    void savePost(UserPost userPost);

    void saveRepost(UserRepost userRepost);

    void deletePost(long postId);

    List<Post> getWall(long userId);

    List<Post> getTimeline(long userId);

    void likePost(Like like);

    void unlikePost(Like like);
}
