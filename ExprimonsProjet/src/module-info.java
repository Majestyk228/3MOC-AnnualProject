module ExprimonsProjet {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires unirest.java;
	
	opens application to javafx.graphics, javafx.fxml;
}
