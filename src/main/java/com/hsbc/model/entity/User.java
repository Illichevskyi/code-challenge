package com.hsbc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsbc.model.dto.UserPost;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.NONE;

@Entity
@Getter
@Setter
@ToString(exclude = {"posts", "followers", "followees", "likedPosts"})
@EqualsAndHashCode(exclude = {"posts", "followers", "followees", "likedPosts"})
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Setter(NONE)
    private long id;
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "user", fetch = LAZY)
    @JsonIgnore
    private Set<Post> posts;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "subscription",
            joinColumns = @JoinColumn(name = "followeeId"),
            inverseJoinColumns = @JoinColumn(name = "followerId")
    )
    @JsonIgnore
    @Setter(NONE)
    private Set<User> followers;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "subscription",
            joinColumns = @JoinColumn(name = "followerId"),
            inverseJoinColumns = @JoinColumn(name = "followeeId")
    )
    @JsonIgnore
    @Setter(NONE)
    private Set<User> followees;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "postReaction",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonIgnore
    @Setter(NONE)
    private Set<Post> likedPosts;

    public User(String name) {
        this.name = name;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(UserPost userPost) {
        this.id = userPost.getUserId();
        this.name = userPost.getUserName();
    }

    public void addFollower(User follower) {
        if (followers == null) {
            followers = new HashSet<>();
        }
        followers.add(follower);
    }

    public void addFollowee(User followee) {
        if (followees == null) {
            followees = new HashSet<>();
        }
        followees.add(followee);
    }

    public void removeFollowee(User followee) {
        if (followees != null) {
            followees.remove(followee);
        }
    }
}
