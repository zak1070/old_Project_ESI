module com.example.turinggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens JavaFxVersion to javafx.fxml;
    exports JavaFxVersion;
}