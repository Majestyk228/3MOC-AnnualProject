module ExprimonsProjet {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	//needed for HTTP
    requires unirest.java;
    
    //needed for JSON
    requires gson;
    requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
