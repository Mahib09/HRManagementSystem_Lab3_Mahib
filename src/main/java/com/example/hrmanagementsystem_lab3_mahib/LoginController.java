package com.example.hrmanagementsystem_lab3_mahib;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;


public class LoginController {
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginMessage;

    String jdbcUrl = "jdbc:mysql://localhost:3306/csd214_mahib_lab3";
    String dbUser = "root";
    String dbPassword = "";
    @FXML
    protected void onLoginClick(){
        String Entered_uname=loginUsername.getText();
        String Entered_password=loginPassword.getText();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            String query="SELECT * FROM tb_users Where username='"+Entered_uname+"' and password='"+Entered_password+"' and type='admin'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                String Name=resultSet.getString("username");
                try {
                    // Load the FXML file for the second scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
                    Parent secondScene = loader.load();

                    // Access the controller of the second scene
                    DashboardController dashboardController = loader.getController();

                    // Set the data in the controller of the second scene
                    //dashboardController.setWelcomeText(Name);

                    // Create a new stage for the second scene
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Dashboard");
                    secondStage.setScene(new Scene(secondScene));

                    // Close the first scene's stage
                    Stage firstSceneStage = (Stage) loginUsername.getScene().getWindow(); // Assuming hbt is a node in the first scene
                    firstSceneStage.close();

                    // Show the second stage
                    secondStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                loginMessage.setText("Invalid UserName or Password");
            }
        }catch (SQLException e) {
            e.printStackTrace();     }

    }
}