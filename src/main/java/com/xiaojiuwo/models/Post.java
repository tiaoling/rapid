package com.xiaojiuwo.models;


import javax.persistence.*;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
