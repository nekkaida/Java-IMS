package com.inventoryapp.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private void handleManageProducts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/inventoryapp/fxml/product_management.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Manage Products");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageCategories() {
        showAlert("Manage Categories clicked!");
    }

    @FXML
    private void handleManageUsers() {
        showAlert("Manage Users clicked!");
    }

    @FXML
    private void handleGenerateReports() {
        showAlert("Generate Reports clicked!");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
