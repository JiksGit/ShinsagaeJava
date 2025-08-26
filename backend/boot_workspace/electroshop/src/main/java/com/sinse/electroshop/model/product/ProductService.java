package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductService {

    public Product regist(Product product);
    public List<Product> getList();
    public Product getDetail(int product_id);
}
