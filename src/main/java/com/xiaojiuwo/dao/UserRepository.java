package com.xiaojiuwo.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Repository("userRepository")
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Object t) {
        entityManager.persist(t);
        return 1l;
    }
}
