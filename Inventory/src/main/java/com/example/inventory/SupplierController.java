package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.XMLFormatter;

public class SupplierController implements initializable{

    @FXML
    private TextField nameTextField,contactTextField;

    @FXML
    private DatePicker datep;

    @FXML
    private BorderPane rootborderpane;

    @FXML
    private Button submitButton;

    @FXML
    private Label successLabel;

    public void submitButtonOnAction(ActionEvent event){
        registerSupplier();
    }

    public void registerSupplier(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String name = nameTextField.getText();
        String contact= contactTextField.getText();
        String date = datep.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        String insertFields= "INSERT INTO supllier(suppliername,suppliercontact,supplierdate) VALUES('";
        String insertValues= name+"','"+contact+"','"+date+"')";
        String insertToRegister= insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            successLabel.setText("Registration created successfully");
            //createProduct();
            BorderPane borderpane= FXMLLoader.load(getClass().getResource("product.fxml"));
            rootborderpane.getChildren().setAll(borderpane);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createProduct(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 628, 407);
            Stage stage = new Stage();
            stage.setTitle("Product Form Management");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

