package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CategoryController implements initializable{
    @FXML
    private TextField category,type;

    @FXML
    private Label successLabel;

    @FXML
    private BorderPane rootborder;

    @FXML
    private Button submitButton;

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String categoryText = category.getText();
        String typeText= type.getText();

        String insertFields= "INSERT INTO category(category,input) VALUES('";
        String insertValues= categoryText+"','"+typeText+"')";
        String insertToRegister= insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            successLabel.setText("successfully Completed Successfully");
            BorderPane borderpane= FXMLLoader.load(getClass().getResource("supplier.fxml"));
            rootborder.getChildren().setAll(borderpane);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitButtonOnAction(ActionEvent event) {
        registerUser();
    }
}
