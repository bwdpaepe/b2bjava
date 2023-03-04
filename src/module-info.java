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
	exports testen;
	exports service;
	exports util;
	exports repository;
	exports gui;

	opens domein;
	opens main;
	opens testen;
	opens service;
	opens util;
	opens repository;
	opens gui;

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
	requires org.junit.jupiter.params;

}