package com.example.CMS_01.Service;// DiscussionService.java
import java.util.List;
import com.example.CMS_01.Entity.DiscussionPosts;

public interface DiscussionService {
    void savePost(DiscussionPosts post);
    List<DiscussionPosts> getAllPosts();
    List<DiscussionPosts> getAllPostsByCourse(String course);
    void destroy();
}
