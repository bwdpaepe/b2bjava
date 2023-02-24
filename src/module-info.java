/**
 * 
 */
/**
 * @author Joachim
 *
 */
module B2B_Portal_Delaware {
	exports domein;
	exports main;
	
	opens domein;
	
	
	// FX
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	// Persistence
	requires java.persistence;
	requires java.sql;
	requires java.instrument;
	// Unit tests
	requires org.junit.jupiter.api;
	requires org.mockito.junit.jupiter;
	requires org.mockito;
	requires jbcrypt;
	
}