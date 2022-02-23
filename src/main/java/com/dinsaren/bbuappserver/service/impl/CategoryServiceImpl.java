package com.dinsaren.bbuappserver.service.impl;

import com.dinsaren.bbuappserver.constants.Constants;
import com.dinsaren.bbuappserver.models.Category;
import com.dinsaren.bbuappserver.payload.response.CategoryRes;
import com.dinsaren.bbuappserver.repository.CategoryRepository;
import com.dinsaren.bbuappserver.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryRes> findAll() {
        List<CategoryRes> resList = new ArrayList<>();
        List<Category> list = categoryRepository.findAllByStatus(Constants.ACTIVE_STATUS);
        list.forEach(c -> {
            CategoryRes res = new CategoryRes();
            res.setData(c);
            resList.add(res);

        });
        return resList;
    }

    @Override
    public void create(Category req) {
        Optional<Category> find = categoryRepository.findById(req.getId());
        if (find.isEmpty()) {
            categoryRepository.save(req);
        }
    }

    @Override
    public void delete(Category req) {
        Optional<Category> find = categoryRepository.findById(req.getId());
        if (find.isEmpty()) {
            req.setStatus(Constants.DELETE_STATUS);
            categoryRepository.save(req);
        }
    }

    @Override
    public void update(Category req) {
        Optional<Category> find = categoryRepository.findById(req.getId());
        if (find.isPresent()) {
            categoryRepository.save(req);
        }
    }

    @Override
    public CategoryRes findById(Long id) {
        CategoryRes res = new CategoryRes();
        Optional<Category> find = categoryRepository.findById(id);
        if(find.isPresent()){
            res.setData(find.get());
            return res;
        }
        return null;
    }
}
