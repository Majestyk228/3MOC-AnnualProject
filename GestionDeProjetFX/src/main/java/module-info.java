module com.exprimonsnousprojet.gestiondeprojetfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.exprimonsnousprojet.gestiondeprojetfx to javafx.fxml;
    exports com.exprimonsnousprojet.gestiondeprojetfx;
}