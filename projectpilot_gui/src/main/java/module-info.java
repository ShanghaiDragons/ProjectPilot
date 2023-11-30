module projectpilot {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens controllers to javafx.fxml;
    exports controllers;

    // opens images to javafx.fxml;
    // exports images;

    opens model to javafx.fxml;
    exports model;

    opens projectpilot to javafx.fxml;
    exports projectpilot;
}
