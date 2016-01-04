package com.xiaojiuwo.services;

import com.xiaojiuwo.models.Post;
import com.xiaojiuwo.models.User;
import com.xiaojiuwo.repositories.PostRepository;
import com.xiaojiuwo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Service
@Transactional
public class PostsService extends BaseService<Post>{

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);

    }

    public List<Post> findAll(){

        return postRepository.findAll();
    }


}
