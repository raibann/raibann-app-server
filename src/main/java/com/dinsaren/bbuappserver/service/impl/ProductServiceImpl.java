package com.dinsaren.bbuappserver.service.impl;

import com.dinsaren.bbuappserver.constants.Constants;
import com.dinsaren.bbuappserver.models.Product;
import com.dinsaren.bbuappserver.payload.request.ProductReq;
import com.dinsaren.bbuappserver.payload.response.ProductRes;
import com.dinsaren.bbuappserver.repository.CategoryRepository;
import com.dinsaren.bbuappserver.repository.ProductRepository;
import com.dinsaren.bbuappserver.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductRes> findAll() {
        List<ProductRes> listRes = new ArrayList<>();
        List<Product> list = productRepository.findAllByStatus(Constants.ACTIVE_STATUS);
        list.forEach(p->{
            ProductRes res = new ProductRes();
            var category = categoryRepository.findById(res.getId());
            res.setData(p, category.get());
            listRes.add(res);
        });
        return listRes;
    }

    @Override
    public void create(ProductReq req) {

    }

    @Override
    public void delete(ProductReq req) {

    }

    @Override
    public void update(ProductReq req) {

    }

    @Override
    public ProductRes findById(Long id) {
        ProductRes res = new ProductRes();
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            var category = categoryRepository.findById(res.getId());
            res.setData(product.get(), category.get());
            return res;
        }
        return null;
    }

    @Override
    public List<ProductRes> findAllByCategoryId(Integer id) {
        List<ProductRes> listRes = new ArrayList<>();
        List<Product> list = productRepository.findAllByCategoryIdAndStatus(id,Constants.ACTIVE_STATUS);
        list.forEach(p->{
            ProductRes res = new ProductRes();
            var category = categoryRepository.findById(res.getId());
            res.setData(p, category.get());
            listRes.add(res);
        });
        return listRes;
    }
}
