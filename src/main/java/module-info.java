module com.searchablebluebook.searchablebluebookv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.searchablebluebookv2 to javafx.fxml;
    exports com.searchablebluebookv2;
}