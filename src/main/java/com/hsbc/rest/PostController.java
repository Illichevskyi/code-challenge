package com.hsbc.rest;

import com.hsbc.model.entity.Post;
import com.hsbc.model.dto.Like;
import com.hsbc.model.dto.UserPost;
import com.hsbc.model.dto.UserRepost;
import com.hsbc.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = POST)
    public void savePost(@RequestBody UserPost userPost) {
        postService.savePost(userPost);
    }

    @RequestMapping(value = "/repost", method = POST)
    public void saveRepost(@RequestBody UserRepost userRepost) {
        postService.saveRepost(userRepost);
    }

    @RequestMapping(value = "/delete/{postId}", method = DELETE)
    public void deletePost(@PathVariable(value = "postId") long postId) {
        postService.deletePost(postId);
    }

    @RequestMapping(value = "/wall/{userId}", method = GET)
    public List<Post> getWall(@PathVariable(value = "userId") long userId) {
        return postService.getWall(userId);
    }

    @RequestMapping(value = "/timeline/{userId}", method = GET)
    public List<Post> getTimeline(@PathVariable(value = "userId") long userId) {
        return postService.getTimeline(userId);
    }

    @RequestMapping(value = "/like", method = POST)
    public void likePost(@RequestBody Like like) {
        postService.likePost(like);
    }

    @RequestMapping(value = "/unlike", method = POST)
    public void unlikePost(@RequestBody Like like) {
        postService.unlikePost(like);
    }
}
