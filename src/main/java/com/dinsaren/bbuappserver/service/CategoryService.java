package com.dinsaren.bbuappserver.service;

import com.dinsaren.bbuappserver.models.Category;
import com.dinsaren.bbuappserver.payload.response.CategoryRes;

import java.util.List;

public interface CategoryService {
    List<CategoryRes> findAll();
    void create(Category req);
    void delete(Category req);
    void update(Category req);
    CategoryRes findById(Long id);
}
