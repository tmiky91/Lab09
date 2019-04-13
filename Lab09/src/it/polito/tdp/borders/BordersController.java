/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	@FXML
    private ComboBox<Country> btnComboBox;
	
	@FXML
	private Button btnConnection;


	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String anno = txtAnno.getText();
		if(anno!=null && !anno.isEmpty()) {
			if(model.annoValido(anno)) {
				txtResult.setText(model.calcolaConfini(anno));
			}
			else {
				showMessage("Scrivi un anno valido");
			}
		}
		else {
			showMessage("Scrivi un anno compreso fra 1816 e il 2016");
		}
	}
	
	 @FXML
	 void doCalcolaConnessioni(ActionEvent event) {
		 String anno = txtAnno.getText();
		 Country c = btnComboBox.getValue();
		 if(anno!=null && !anno.isEmpty()) {
			 if(model.annoValido(anno)) {
				 if(c!=null) {
					 if(anno!=null && !anno.isEmpty() && c!=null) {
						 txtResult.setText(model.calcolaConnessioniDelloStato(anno, c));
					 }
					 else {
						 showMessage("Seleziona uno Stato dal menù a tendina e scrivi un anno compreso fra 1816 e il 2016");
					 }
				 }
				 else {
					 showMessage("Seleziona uno Stato dal menù a tendina");
				 }
			 }
			 else {
				 showMessage("Scrivi un anno valido");
			 }
		 }
		 else {
			 showMessage("Scrivi un anno compreso fra 1816 e il 2016");
		 }
//		 	String anno = txtAnno.getText();
//	    	Country c = btnComboBox.getValue();
//	    	if(anno!=null && !anno.isEmpty()) {
//	    		if(model.annoValido(anno)) {
//	    			if(c!=null) {
//	    				txtResult.setText(model.calcolaConnessioniDelloStato(anno, c));
//	    			}
//	    			else {
//	    				showMessage("Seleziona uno Stato dal menù a tendina");
//	    			}
//	    		}
//	    		else {
//	    			showMessage("Scrivi un anno valido");
//	    		}
//	    	}
//	    	else {
//	    		showMessage("Scrivi un anno compreso fra 1816 e il 2016");
//	    	}
	 }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnConnection != null : "fx:id=\"btnConnection\" was not injected: check your FXML file 'Borders.fxml'.";
        
        btnComboBox.getItems().addAll(Model.getAllStates());
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	private void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
}
