package com.Project.backend.Repository;

import com.Project.backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
