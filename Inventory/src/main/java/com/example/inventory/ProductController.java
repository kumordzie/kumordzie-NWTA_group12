package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ProductController implements initializable{

    @FXML
    private TextField nameTextField,quantityTextField;

    @FXML
    private BorderPane rootborderpane;

    @FXML
    private DatePicker datep;

    @FXML
    private Pane paneloader;

    @FXML
    private Label successLabel;

    @FXML
    private Button submitButton;

    public void submitButtonOnAction(ActionEvent event){
        registerProduct();
    }

    public void registerProduct(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String name = nameTextField.getText();
        String date = datep.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String quantity= quantityTextField.getText();

        String insertFields= "INSERT INTO product(productname,intakedate,quantity) VALUES('";
        String insertValues= name+"','"+date+"','"+quantity+"')";
        String insertToRegister= insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            successLabel.setText("Product registered successfully");
            //createPrice();
            BorderPane borderpane= FXMLLoader.load(getClass().getResource("price.fxml"));
            paneloader.getChildren().setAll(borderpane);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createPrice(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("price.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 628, 407);
            Stage stage = new Stage();
            stage.setTitle("Price Form Management");
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


    public void priceButtonOnAction(ActionEvent actionEvent) throws IOException {
        registerProduct();

    }
}
