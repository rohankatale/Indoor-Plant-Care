package org.example.plantmanagement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PlantFormController {

    @FXML
    private TextField plantNameField;

    @FXML
    private ComboBox<String> plantTypeComboBox;


    @FXML
    private ImageView plantImageView;

    @FXML
    private ComboBox<String> waterReminderComboBox;

    @FXML
    private ComboBox<String> fertilizerReminderComboBox;

    private HomeController homeController;
    private Image selectedImage;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    private void initialize() {
        // Set the prompt text for the ComboBoxes
        plantTypeComboBox.setPromptText("Select Plant Type");
        waterReminderComboBox.setPromptText("Select Water Reminder");
        fertilizerReminderComboBox.setPromptText("Select Fertilizer Reminder");

        // Populate the ComboBoxes with sample data
        plantTypeComboBox.setItems(FXCollections.observableArrayList("Fruits", "Shrubs", "Herbs","Vegetables"));
        waterReminderComboBox.setItems(FXCollections.observableArrayList("8 hours", "16 hours", "24 hours"));
        fertilizerReminderComboBox.setItems(FXCollections.observableArrayList("1 week", "2 weeks", "3 weeks"));
    }

    @FXML
    private void handleSelectImageButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(plantImageView.getScene().getWindow());
        if (file != null) {
            selectedImage = new Image(file.toURI().toString());
            plantImageView.setImage(selectedImage);
        }
    }

    @FXML
    private void handleSubmitButton() {
        // Handle form submission here
        String plantName = plantNameField.getText();
        String plantType = plantTypeComboBox.getValue();
        String waterReminder = waterReminderComboBox.getValue();
        String fertilizerReminder = fertilizerReminderComboBox.getValue();

        // Close the form
        Stage stage = (Stage) plantNameField.getScene().getWindow();
        stage.close();

        // Update the home page with the new plant
        if (homeController != null) {
            homeController.addPlantToList(plantName, plantType, waterReminder, fertilizerReminder, selectedImage);
        }
    }
}