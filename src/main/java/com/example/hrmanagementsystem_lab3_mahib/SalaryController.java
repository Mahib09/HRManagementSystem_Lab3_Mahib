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

public class SalaryController implements Initializable {
    @FXML
    private Label TitleText;
    @FXML
    private TextField inputUserId;
    @FXML
    private TextField inputSalaryId;
    @FXML
    private TextField inputDate;
    @FXML
    private TextField inputAmount;
    @FXML
    private Label lblMessage;
    @FXML
    private TableView<SalaryDAO> tbl_view;
    @FXML
    private TableColumn<SalaryDAO, Integer> tbl_salaryid;
    @FXML
    private TableColumn<SalaryDAO, Integer> tbl_userId;
    @FXML
    private TableColumn<SalaryDAO, String> tbl_amount;
    @FXML
    private TableColumn<SalaryDAO, String> tbl_date;

    ObservableList<SalaryDAO> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_salaryid.setCellValueFactory(new PropertyValueFactory<SalaryDAO, Integer>("salaryId"));
        tbl_userId.setCellValueFactory(new PropertyValueFactory<SalaryDAO, Integer>("userId"));
        tbl_amount.setCellValueFactory(new PropertyValueFactory<SalaryDAO, String>("amount"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<SalaryDAO, String>("date"));
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
            String query = "SELECT * FROM `tb_salary`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int tbl_salaryid= resultSet.getInt("salaryId");
                int tbl_userId = resultSet.getInt("userId");
                Float tbl_amount = resultSet.getFloat("amountpaid");
                String tbl_date = resultSet.getString("Date");
                tbl_view.getItems().add(new SalaryDAO(tbl_salaryid,tbl_userId,tbl_amount,tbl_date));
            }     } catch (SQLException e) {
            e.printStackTrace();     }
    }
    public void insertData() {
        if (inputUserId.getText().isEmpty()) {
            lblMessage.setText("userId Required to Insert");
        } else {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = "INSERT INTO tb_salary (userId, amountpaid, Date) VALUES ('" + inputUserId.getText() + "','" + inputAmount.getText() + "','"+inputDate.getText()+"')";
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
        if(inputSalaryId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Update");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = ("UPDATE tb_salary SET userId ='"+inputUserId.getText()+"' , amountpaid='"+inputAmount.getText()+"', Date='"+inputDate.getText()+"' WHERE salaryId='"+inputSalaryId.getText()+"';");
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }
    }
    public void deleteData(){
        if(inputSalaryId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Delete");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = ("DELETE FROM tb_salary WHERE salaryId='"+inputSalaryId.getText()+"'");
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }
    }
    public void loadData(){
        if(inputSalaryId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Load");
        }else{try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = ("SELECT * FROM tb_salary WHERE salaryId='"+inputSalaryId.getText()+"'");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            inputUserId.setText(resultSet.getString("userId"));
            inputAmount.setText(resultSet.getString("amountpaid"));
            inputDate.setText(resultSet.getString("Date"));
            lblMessage.setText("");
        } catch (SQLException e) {
            e.printStackTrace();     }}
    }
    public void clearForm(){
        inputSalaryId.setText("");
        inputUserId.setText("");
        inputAmount.setText("");
        inputDate.setText("");

    }


    public static double totalSalary(double salary){
        double annualsalary=12*salary;
        return annualsalary;
    }
}
