package com.hsbc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.NONE;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "originalPosts", "reactedUsers"})
@ToString(exclude = {"user", "originalPosts", "reactedUsers"})
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Setter(NONE)
    private long id;

    @Size(max = 140, message = "Post cannot be longer than 140 characters")
    private String text;

    @JsonIgnore
    @Setter(NONE)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "originalPosts",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "originalPostId")
    )
    @Setter(NONE)
    private Set<Post> originalPosts;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "postReaction",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "postId")
    )
    @Setter(NONE)
    private Set<User> reactedUsers;

    public Post() {
        setDateTime();
    }

    public Post(long id, String text) {
        setDateTime();
        this.id = id;
        this.text = text;
    }

    public Post(String text, User user) {
        setDateTime();
        this.text = text;
        this.user = user;
    }

    public Post(User user, Post originalPosts) {
        setDateTime();
        this.text = originalPosts.getText();
        this.user = user;
        addRepost(originalPosts);
    }

    private void addRepost(Post originalPost) {
        if (this.originalPosts == null) {
            this.originalPosts = new HashSet<>();
        }
        this.originalPosts.add(originalPost);
    }

    private void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }

    public void likePost(User user) {
        if (reactedUsers == null) {
            reactedUsers = new HashSet<>();
        }
        reactedUsers.add(user);
    }

    public void unlikePost(User user) {
        if (reactedUsers != null) {
            reactedUsers.remove(user);
        }
    }
}
