package com.xiaojiuwo.repositories;

import com.xiaojiuwo.models.Post;
import com.xiaojiuwo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


/**
 * Created by liuhaibao on 16/1/1.
 */
@RepositoryDefinition(domainClass = Post.class, idClass = Long.class)
public interface PostRepository extends Repository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    List<Post> findAll();

    Post findByTitle(String title);

    Post save(Post post);
}
