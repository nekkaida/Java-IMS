package com.inventoryapp.ui;

import com.inventoryapp.model.Category;
import com.inventoryapp.model.Product;
import com.inventoryapp.service.InventoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class ProductManagementController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField stockField;

    @FXML
    private ComboBox<Category> categoryComboBox;  // ComboBox for selecting categories

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    private InventoryService inventoryService = new InventoryService();

    @FXML
    public void initialize() {
        // Initialize the product table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Load products from the database and display them in the table
        loadProducts();

        // Populate the ComboBox with categories from the database
        List<Category> categories = inventoryService.getAllCategories();
        categoryComboBox.getItems().addAll(categories);
    }

    @FXML
    private void handleAddProduct() {
        // Retrieve input values
        String name = nameField.getText();
        String description = descriptionField.getText();
        String priceText = priceField.getText();
        String stockText = stockField.getText();
        Category selectedCategory = categoryComboBox.getValue();  // Get selected category from ComboBox

        // Validate input
        if (name == null || name.isEmpty()) {
            showAlert("Product name is required.");
            return;
        }

        if (description == null || description.isEmpty()) {
            showAlert("Product description is required.");
            return;
        }

        if (priceText == null || priceText.isEmpty()) {
            showAlert("Product price is required.");
            return;
        }

        if (stockText == null || stockText.isEmpty()) {
            showAlert("Product stock quantity is required.");
            return;
        }

        double price;
        int stock;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            showAlert("Product price must be a valid number.");
            return;
        }

        try {
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException e) {
            showAlert("Product stock quantity must be a valid number.");
            return;
        }

        if (selectedCategory == null) {
            showAlert("Please select a category for the product.");
            return;
        }

        // If all validation passes, create a new product
        Product product = new Product(name, description, price, stock, selectedCategory.getId());
        inventoryService.addProduct(product);
        loadProducts();  // Refresh the product list
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void handleUpdateProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setName(nameField.getText());
            selectedProduct.setDescription(descriptionField.getText());
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setStock(Integer.parseInt(stockField.getText()));
            selectedProduct.setCategoryId(categoryComboBox.getValue().getId());

            inventoryService.updateProduct(selectedProduct);
            loadProducts();
        } else {
            showAlert("Please select a product to update.");
        }
    }

    @FXML
    private void handleDeleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            inventoryService.deleteProduct(selectedProduct.getId());
            loadProducts();
        } else {
            showAlert("Please select a product to delete.");
        }
    }

    private void loadProducts() {
        List<Product> products = inventoryService.getAllProducts();
        productTable.getItems().clear();
        productTable.getItems().addAll(products);
    }

}
