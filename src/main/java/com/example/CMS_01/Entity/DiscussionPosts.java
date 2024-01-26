package com.example.CMS_01.Entity;// DiscussionPosts.java
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class DiscussionPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    private String course;
    @NonNull
    private String username;
    @NonNull
    private String post;

    private Date createdDate;

}
