package com.Project.backend.service.impl;

import com.Project.backend.Dto.PostDto;
import com.Project.backend.Exception.ResourceNotFoundException;
import com.Project.backend.Model.Post;
import com.Project.backend.Repository.PostRepository;
import com.Project.backend.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServceImpl implements PostService {

    private PostRepository postRepository;


    public PostServceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
   public Post createPost(Post entity) {
        //Convert Dto to Entity
        entity = postRepository.save(entity);

       return entity;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();

    }

    @Override
    public Post getPostById(long id) {
       Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
       return post;
    }

    @Override
    public PostDto updateById(PostDto postDto,long id) {
       Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
       post.setTitle(postDto.getTitle());
       post.setDescription(postDto.getDescripttion());
       post.setContent(postDto.getContent());
       Post updatedPost=postRepository.save(post);
       return mapToDTO(updatedPost);

    }

    @Override
    public void DeleteById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.deleteById(id);
    }

    //Convert Entity to Dto
    private PostDto mapToDTO(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescripttion(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;

    }
    //convert Dto to Entity
    private Post maptoEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescripttion());
        post.setContent(postDto.getContent());
        return post;
    }

}
