module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com;
    opens com to javafx.fxml;
    exports com.Controllers;
    opens com.Controllers to javafx.fxml;
    exports com.SignIn;
    opens com.SignIn to javafx.fxml;
    opens com.Classes to javafx.base, javafx.controls, javafx.fxml;


}