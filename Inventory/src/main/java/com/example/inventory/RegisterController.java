package com.example.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements initializable{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button registerButton;

    @FXML
    private BorderPane rootborderpane;
    @FXML
    private Label sucessLabel,userLabel;
    public void registerButtonOnAction(ActionEvent event){

        if(setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
        }
        else{
            wrongPasswordLabel.setText("Password does not match");
        }
    }

    @FXML
    private PasswordField setPasswordField,confirmPasswordField;
    @FXML
    private Label wrongPasswordLabel;
    @FXML
    private TextField firstnameTextField,lastnameTextField,usernameTextField;
    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname= lastnameTextField.getText();
        String username= usernameTextField.getText();
        String password= setPasswordField.getText();

        String insertFields= "INSERT INTO user_account(firstname,lastname,username,password) VALUES('";
        String insertValues= firstname+"','"+lastname+"','"+username+"','"+password+"')";
        String insertToRegister= insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            sucessLabel.setText("successful");
            BorderPane borderpane= FXMLLoader.load(getClass().getResource("category.fxml"));
            rootborderpane.getChildren().setAll(borderpane);
        }
        catch(Exception e){
//            e.printStackTrace();
//            e.getCause();
            //userLabel.setText(String.valueOf(e));
            userLabel.setText("User name already exists, enter new username");
        }
    }


    public void loginButtonOnClick(ActionEvent event) throws IOException {
        BorderPane borderpane= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        rootborderpane.getChildren().setAll(borderpane);
    }
}

