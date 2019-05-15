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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
    private Button btnTrovaVicini;
	
	@FXML
    private ComboBox<Country> btnComboBox;
	
	

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String anno = txtAnno.getText();
		if(anno!=null || !anno.isEmpty()) {
			if(model.isDigit(anno)) {
				txtResult.appendText(model.calcolaConfini(anno));
				txtResult.appendText("Grafo creato: "+model.getNumeroVertici()+" vertici e "+model.getNumeroArchi()+" archi\n");
				btnTrovaVicini.setDisable(false);
			}
			else {
				showMessage("Errore: Inserisci un anno valido");
			}
		}
		else {
			showMessage("Errore: Inserisci un anno compreso fra il 1816 e il 2016");
		}
	}
	
	@FXML
    void doTrovaVicini(ActionEvent event) {
		Country c = btnComboBox.getValue();
		if(c!=null) {
			txtResult.setText(model.trovaVicini(c));
		}
		else {
			showMessage("Errore: Seleziona uno Stato dal menù a tendina");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Borders.fxml'.";
		assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'Borders.fxml'.";
		
	}

	public void setModel(Model model) {
		this.model=model;
		btnComboBox.getItems().addAll(model.getAllCountry());
		btnTrovaVicini.setDisable(true);
		
	}
	
	 private void showMessage(String message) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(message);
			alert.show();
		}
}
