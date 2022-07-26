module ExprimonsProjet {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires unirest.java;
	requires com.fasterxml.jackson.databind;
	
	opens application to javafx.graphics, javafx.fxml;
}
