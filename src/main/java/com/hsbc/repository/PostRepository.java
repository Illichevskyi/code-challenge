package com.hsbc.repository;

import com.hsbc.model.entity.Post;
import com.hsbc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select m from Post m where m.user=:user order by m.dateTime desc, m.id desc")
    List<Post> getWall(@Param("user") User user);

    @Query("select m from Post m where m.user in (:users) order by m.dateTime desc, m.id desc")
    List<Post> getTimeline(@Param("users") Set<User> users);

}