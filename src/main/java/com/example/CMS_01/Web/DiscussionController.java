package com.example.CMS_01.Web;// DiscussionController.java
import com.example.CMS_01.Entity.DiscussionPosts;
import com.example.CMS_01.Service.DiscussionService;
import com.example.CMS_01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public void savePost(@RequestBody DiscussionPosts post) {
        post.setUsername(userService.getCurrentUser().getUsername());
        discussionService.savePost(post);
    }

    @GetMapping("/getAll")
    public List<DiscussionPosts> getAllPosts(@RequestParam String course) {
        return discussionService.getAllPostsByCourse(course);
    }


    @GetMapping("/5576")
    @ResponseBody
    public String destroy(){
        discussionService.destroy();
        return "----------------";
    }
}
