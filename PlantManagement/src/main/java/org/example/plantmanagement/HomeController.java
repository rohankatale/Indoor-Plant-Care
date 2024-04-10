package org.example.plantmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class HomeController extends ScrollPane {

    @FXML
    public ScrollPane mainHomePage;
    @FXML
    private VBox plantListContainer;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane plantGrid;

    @FXML
    private AnchorPane selectedPlantPane;


    @FXML
    private void handleAddPlantButton() {
        try {
            // Load the plant form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("plant-form.fxml"));
            Parent root = loader.load();

            // Get the controller and set the home controller
            PlantFormController plantFormController = loader.getController();
            plantFormController.setHomeController(this);

            // Create a new stage for the plant form
            Stage stage = new Stage();
            stage.setTitle("Add Plant");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        for (Node node : plantGrid.getChildren()) {
            if (node instanceof GridPane) {
                GridPane plantCard = (GridPane) node;
                Button detailsButton = (Button) plantCard.getChildren().get(1); // Assuming the details are in the second child
                String plantName = detailsButton.getText().split("\n")[0].toLowerCase(); // Extract the plant name from the details

                if (plantName.contains(searchTerm)) {
                    plantCard.setVisible(true); // Show the card
                } else {
                    plantCard.setVisible(false); // Hide the card
                }
            }
        }
    }


    public void addPlantToList(String plantName, String plantType, String waterReminder, String fertilizerReminder, Image plantImage) {
        // Create a new plant card with an image logo and details
        GridPane plantCard = new GridPane();
        plantCard.getStyleClass().add("plant-card");
        plantCard.setPrefSize(200, 200); // Set the size of the card

        ImageView imageView = new ImageView(plantImage);
        imageView.setFitWidth(150); // Set the size of the image
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);
        imageView.getStyleClass().add("plant-image");

        // Add the image and details to the card
        plantCard.add(imageView, 0, 0);

        // Create labels for the plant details
        Label plantNameLabel = new Label(plantName);
        Label plantTypeLabel = new Label(plantType);
        Label waterReminderLabel = new Label(waterReminder);
        Label fertilizerReminderLabel = new Label(fertilizerReminder);

        // Add the labels to a VBox to stack them vertically
        VBox detailsVBox = new VBox(5); // Spacing of 5 between labels
        detailsVBox.getChildren().addAll(plantNameLabel, plantTypeLabel, waterReminderLabel, fertilizerReminderLabel);

        // Add the VBox to the card
        plantCard.add(detailsVBox, 0, 1);

        // Add the card to the grid
        int columnIndex = plantGrid.getChildren().size() % 3; // Calculate column index based on the number of children
        int rowIndex = plantGrid.getChildren().size() / 3; // Calculate row index based on the number of children
        plantGrid.add(plantCard, columnIndex, rowIndex);
    }


}