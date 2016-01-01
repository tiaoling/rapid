package com.xiaojiuwo.repositories;

import com.xiaojiuwo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * Created by liuhaibao on 16/1/1.
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository extends Repository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByName(String name);

    User save(User user);
}
