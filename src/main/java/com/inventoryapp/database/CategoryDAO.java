package com.inventoryapp.database;

import com.inventoryapp.model.Category;
import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
}
