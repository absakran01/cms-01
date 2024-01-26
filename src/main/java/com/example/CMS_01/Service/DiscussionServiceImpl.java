package com.example.CMS_01.Service;// DiscussionServiceImpl.java
import com.example.CMS_01.Entity.DiscussionPosts;
import com.example.CMS_01.Repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;
    @Override
    public void savePost(DiscussionPosts post) {
        DiscussionPosts postDTO = new DiscussionPosts();

        postDTO.setCreatedDate(new Date());
        postDTO.setUsername(post.getUsername());
        postDTO.setPost(post.getPost());

        discussionRepository.save(post);
    }


    @Override
    public List<DiscussionPosts> getAllPosts() {
        return (List<DiscussionPosts>) discussionRepository.findAll();
    }

    /**
     * @param course
     * @return list of all the posts made to a certain course
     */
    @Override
    public List<DiscussionPosts> getAllPostsByCourse(String course) {
        return discussionRepository.findAllByCourse(course);
    }

    /**
     *
     */
    @Override
    public void destroy() {
        discussionRepository.deleteAll();

    }
}
