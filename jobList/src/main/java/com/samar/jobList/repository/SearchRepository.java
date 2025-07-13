package com.samar.jobList.repository;

import com.samar.jobList.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
