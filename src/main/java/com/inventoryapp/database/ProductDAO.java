package com.inventoryapp.database;

import com.inventoryapp.model.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    Product getProduct(int id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);
}
