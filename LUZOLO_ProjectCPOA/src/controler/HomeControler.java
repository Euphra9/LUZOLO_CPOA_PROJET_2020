package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class HomeControler implements Initializable{

	@FXML
	private Label lbl_bienvenue;
	
	@FXML RadioButton rb_sql,rb_listem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Boolean valeurRB() {
		if (rb_sql.isSelected()) {
		 return true;
		}
		else return false;
	}

	@FXML
	public void openProduitStage() throws IOException {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/produits.fxml"));

		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage produit=new Stage();
			
			produit.setScene(scene);
			produit.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fermeture de la fenetre precedente
		
		Stage home=(Stage) lbl_bienvenue.getScene().getWindow();
		home.close();
		
		}
	
	@FXML
	public void openClientStage() throws IOException {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/clients.fxml"));
		

		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage client=new Stage();
			
			client.setScene(scene);
			client.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fermeture de la fenetre precedente
		
		Stage home=(Stage) lbl_bienvenue.getScene().getWindow();
		home.close();
		
		}
	
	@FXML
	public void openCategorieStage() throws IOException {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/categorie.fxml"));

		
		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage categorie=new Stage();
			
			categorie.setScene(scene);
			categorie.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fermeture de la fenetre precedente
		
		Stage home=(Stage) lbl_bienvenue.getScene().getWindow();
		home.close();
		
		
		}
	
	@FXML
	public void openCommandeStage() throws IOException {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/commandes.fxml"));
		
		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage commande=new Stage();
			
			commande.setScene(scene);
			commande.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fermeture de la fenetre precedente
		
		Stage home=(Stage) lbl_bienvenue.getScene().getWindow();
		home.close();
		
		}
		
	

}
