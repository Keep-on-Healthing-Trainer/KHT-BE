package com.example.khtbe.domain.post.domain;

import com.example.khtbe.domain.comment.domain.Comment;
import com.example.khtbe.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    private String path;
  
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(User user, String title, String content, String language){
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public String updatePath(String path) {
        this.path = path;
        return this.path;
    }
}
