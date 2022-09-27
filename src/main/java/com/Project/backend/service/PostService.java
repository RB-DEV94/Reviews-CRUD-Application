package com.Project.backend.service;

import com.Project.backend.Dto.PostDto;
import com.Project.backend.Model.Post;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    Post createPost(Post post);
    List<Post> getAllPosts();

    Post getPostById(long id);

    PostDto updateById(PostDto postDto,long id);

    void DeleteById(long id);
}
