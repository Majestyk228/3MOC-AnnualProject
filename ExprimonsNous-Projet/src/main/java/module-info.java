module com.exprimonsnous.projet.exprimonsnousprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.exprimonsnous.projet.exprimonsnousprojet to javafx.fxml;
    exports com.exprimonsnous.projet.exprimonsnousprojet;
}