package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.metier.Client;
import model.metier.Produit;

public class ControlerCommande implements Initializable{
	DaoFactory daoF;

	
	@FXML TextField tf_qte;
	
	@FXML private ChoiceBox<Client> cbx_client;
	@FXML private ChoiceBox<Produit> cbx_produit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		this.cbx_client.setItems(FXCollections.observableArrayList(daoF.getDaoClient().findAll()));
		this.cbx_produit.setItems(FXCollections.observableArrayList(daoF.getDaoProduit().findAll()));


		
	}
	
	@FXML
	public void revenirAccueil() {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/accueil.fxml"));

		
		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage home=new Stage();
			
			home.setScene(scene);
			home.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage commande=(Stage)tf_qte.getScene().getWindow();
		commande.close();
		
	}

}
