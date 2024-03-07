package com.example.hrmanagementsystem_lab3_mahib;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DashboardController {
    @FXML
    private Label WelcomeText;
    @FXML
    private Label title;
    public void setWelcomeText(String Name){
        WelcomeText.setText("Hello, "+ Name+"! "+ LocalDate.now());
    }
    @FXML protected void onDashEmployeeClicked(){DashEmployeeClicked();}
    @FXML protected void onDashSalaryClicked(){DashSalaryClicked();}
    @FXML protected void onDashLogoutClicked(){DashLogoutClicked();}
    @FXML protected void onDashExitClicked(){DashExitClicked();}

    public void DashEmployeeClicked(){
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employee-view.fxml"));
            Parent secondScene = loader.load();

            // Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Employee Information");
            secondStage.setScene(new Scene(secondScene));

            // Close the first scene's stage
            Stage firstSceneStage = (Stage) title.getScene().getWindow(); // Assuming hbt is a node in the first scene
            firstSceneStage.close();

            // Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DashSalaryClicked(){
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("salary-view.fxml"));
            Parent secondScene = loader.load();

            // Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Salary Information");
            secondStage.setScene(new Scene(secondScene));

            // Close the first scene's stage
            Stage firstSceneStage = (Stage) WelcomeText.getScene().getWindow(); // Assuming hbt is a node in the first scene
            firstSceneStage.close();

            // Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DashLogoutClicked(){
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent secondScene = loader.load();

            // Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Login");
            secondStage.setScene(new Scene(secondScene));

            // Close the first scene's stage
            Stage firstSceneStage = (Stage) WelcomeText.getScene().getWindow(); // Assuming hbt is a node in the first scene
            firstSceneStage.close();

            // Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void DashExitClicked(){
        System.exit(0);

    }
}
