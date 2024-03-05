module com.example.hrmanagementsystem_lab3_mahib {
    requires javafx.controls;
    requires javafx.fxml;
            
                                requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.hrmanagementsystem_lab3_mahib to javafx.fxml;
    exports com.example.hrmanagementsystem_lab3_mahib;
}