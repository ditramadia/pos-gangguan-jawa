module com.example.if2210_tb2_nge {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
            requires eu.hansolo.tilesfx;
            requires com.almasb.fxgl.all;
            requires lombok;

    opens com.example.if2210_tb2_nge to javafx.fxml;
    exports com.example.if2210_tb2_nge;
}