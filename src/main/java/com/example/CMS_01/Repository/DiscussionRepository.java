package com.example.CMS_01.Repository;

import com.example.CMS_01.Entity.DiscussionPosts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiscussionRepository extends CrudRepository<DiscussionPosts, Long> {
    List<DiscussionPosts> findAllByCourse(String Course);
}
