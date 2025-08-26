package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public Product regist(Product product) {
        return productDAO.save(product);
    }

    @Override
    public List<Product> getList() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getDetail(int product_id) {
        return productDAO.findById(product_id);
    }
}
