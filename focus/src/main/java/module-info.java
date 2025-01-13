module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    

    exports com.focus.view;
    exports com.focus.controllers;
   

    opens com.focus.view to javafx.fxml;
    opens com.focus.controllers to javafx.fxml;

}
