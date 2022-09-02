package com.example.inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    @FXML
    private Button signupButton,loginButton;

    @FXML
    private BorderPane rootborderpane;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;


    public void loginButtonOnClick(ActionEvent event){
        loginMessageLabel.setText("Login Attempted");
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    private void validateLogin() {
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();

        String verifyLogin= "SELECT count(1) FROM user_account WHERE username='" + usernameTextField.getText() + "' AND password='" +enterPasswordField.getText()+ "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
//                   loginMessageLabel.setText("Login Successful");
                    BorderPane borderpane= FXMLLoader.load(getClass().getResource("category.fxml"));
                    rootborderpane.getChildren().setAll(borderpane);
                }
                else{
                    loginMessageLabel.setText("Wrong Login, Retry");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signupOnAction(ActionEvent event) throws IOException {
        BorderPane borderpane= FXMLLoader.load(getClass().getResource("register.fxml"));
        rootborderpane.getChildren().setAll(borderpane);

    }
}