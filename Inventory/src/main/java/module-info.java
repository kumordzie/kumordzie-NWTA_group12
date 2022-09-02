module com.example.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;



    opens com.example.inventory to javafx.fxml;
    exports com.example.inventory;
}