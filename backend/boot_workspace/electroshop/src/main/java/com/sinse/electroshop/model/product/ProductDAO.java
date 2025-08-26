package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductDAO {

    public Product save(Product product);
    public Product update(Product product);
    public List<Product> getAllProducts();
    public Product findById(int product_id);
    public void delete(Product product);
}
