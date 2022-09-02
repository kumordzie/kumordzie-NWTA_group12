package com.example.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PriceController implements initializable{
    @FXML
    private TextField unitcost,bulkcost,unitsell,bulksell,discount;

    @FXML
    private Label successLabel;

    @FXML
    private BorderPane rootborder;

    @FXML
    private Button submitButton;

    public void registerPrice(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String unitcostText = unitcost.getText();
        String bulkcostText= bulkcost.getText();
        String unitsellText= unitsell.getText();
        String bulksellText= bulksell.getText();
        String discountText= discount.getText();


        String insertFields= "INSERT INTO price(unitcostprice,bulkcostprice,unitsellingprice,bulksellingprice,discount) VALUES('";
        String insertValues= unitcostText+"','"+bulkcostText+"','"+unitsellText+"','"+bulksellText+"','"+discountText+"')";
        String insertToRegister= insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            successLabel.setText(" successfully completed");
            //createCategory();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createCategory(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Category Form Management");
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


    public void submitOnAction(ActionEvent event) {
        registerPrice();
    }
}
