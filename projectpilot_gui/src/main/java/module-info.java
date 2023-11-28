module projectpilot {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens controllers to javafx.fxml;
    exports controllers;

    // opens lib to javafx.fxml;
    // exports lib;

    opens model to javafx.fxml;
    exports model;

    opens projectpilot to javafx.fxml;
    exports projectpilot;
}
