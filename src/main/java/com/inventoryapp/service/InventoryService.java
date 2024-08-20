package com.inventoryapp.service;

import com.inventoryapp.database.CategoryDAO;
import com.inventoryapp.database.CategoryDAOImpl;
import com.inventoryapp.database.ProductDAO;
import com.inventoryapp.database.ProductDAOImpl;
import com.inventoryapp.model.Category;
import com.inventoryapp.model.Product;

import java.util.List;

public class InventoryService {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public InventoryService() {
        // Initialize DAOs
        this.productDAO = new ProductDAOImpl();
        this.categoryDAO = new CategoryDAOImpl();
    }

    public void addProduct(Product product) {
        // Additional business logic (e.g., validation) can go here
        productDAO.addProduct(product);
    }

    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
