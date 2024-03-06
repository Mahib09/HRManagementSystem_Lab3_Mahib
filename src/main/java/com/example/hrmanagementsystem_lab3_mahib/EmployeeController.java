package com.example.hrmanagementsystem_lab3_mahib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable{
    @FXML
    private Label TitleText;
    @FXML
    private TextField inputUserId;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputEmail;
    @FXML
    private Label lblMessage;
    @FXML
    private TableView<EmployeeDAO> tbl_view;
    @FXML
    private TableColumn<EmployeeDAO, Integer> tbl_userId;
    @FXML
    private TableColumn<EmployeeDAO, String> tbl_name;
    @FXML
    private TableColumn<EmployeeDAO, String> tbl_email;

    ObservableList<EmployeeDAO> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_userId.setCellValueFactory(new PropertyValueFactory<EmployeeDAO, Integer>("userId"));
        tbl_name.setCellValueFactory(new PropertyValueFactory<EmployeeDAO, String>("Name"));
        tbl_email.setCellValueFactory(new PropertyValueFactory<EmployeeDAO, String>("Email"));
        tbl_view.setItems(list);
    }

    String jdbcUrl = "jdbc:mysql://localhost:3306/csd214_mahib_lab3";
    String dbUser = "root";
    String dbPassword = "";

    @FXML
    protected void onBackClicked() {BackClicked();}
    @FXML
    protected void onLogoutClicked() {LogoutClicked();}
    @FXML
    protected void onReadButtonClicked() {populateTable();}
    @FXML
    protected void onInsertButtonClicked() {insertData();}
    @FXML
    protected void onUpdateButtonClicked() {updateData();}
    @FXML
    protected void onDeleteButtonClicked() {deleteData();}
    @FXML
    protected void onLoadButtonClicked() {loadData();}
    public void BackClicked(){
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent secondScene = loader.load();

            // Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Dashboard");
            secondStage.setScene(new Scene(secondScene));

            // Close the first scene's stage
            Stage firstSceneStage = (Stage) TitleText.getScene().getWindow(); // Assuming hbt is a node in the first scene
            firstSceneStage.close();

            // Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void LogoutClicked(){
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent secondScene = loader.load();

            // Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Login");
            secondStage.setScene(new Scene(secondScene));

            // Close the first scene's stage
            Stage firstSceneStage = (Stage) TitleText.getScene().getWindow(); // Assuming hbt is a node in the first scene
            firstSceneStage.close();

            // Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void populateTable(){
        list.clear();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM `tb_users`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int tbl_userId = resultSet.getInt("userId");
                String tbl_name = resultSet.getString("Name");
                String tbl_email = resultSet.getString("Email");
                tbl_view.getItems().add(new EmployeeDAO(tbl_userId,tbl_name,tbl_email));
            }     } catch (SQLException e) {
            e.printStackTrace();     }
    }
    public void insertData() {
        if (inputName.getText().isEmpty()) {
            lblMessage.setText("Name Required to Insert");
        } else {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = "INSERT INTO tb_users (Name, Email, username, password, type) VALUES ('" + inputName.getText() + "','" + inputEmail.getText() + "','','','')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateData(){
        if(inputUserId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Update");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = ("UPDATE tb_users SET Name ='"+inputName.getText()+"' , Email='"+inputEmail.getText()+"' WHERE userId='"+inputUserId.getText()+"';");
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }
    }
    public void deleteData(){
        if(inputUserId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Delete");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = ("DELETE FROM tb_users WHERE userId='"+inputUserId.getText()+"'");
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }
    }
    public void loadData(){
        if(inputUserId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Load");
        }else{try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = ("SELECT * FROM tb_users WHERE userId='"+inputUserId.getText()+"'");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            inputName.setText(resultSet.getString("Name"));
            inputEmail.setText(resultSet.getString("Email"));
            lblMessage.setText("");
        } catch (SQLException e) {
            e.printStackTrace();     }}
    }
    public void clearForm(){
        inputUserId.setText("");
        inputName.setText("");
        inputEmail.setText("");
    }

}
