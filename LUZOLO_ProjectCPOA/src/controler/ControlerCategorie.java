package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Connexion;

public class ControlerCategorie implements Initializable {

	DaoFactory daoF;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		
	}
	
	@FXML
	private Label lbl_nom,lbl_visuel;
	
	@FXML
	private TextField tf_titre,tf_visuel;
	
	public void newModelCategorie() {
		boolean valide=false;
		
		if(!this.tf_titre.getText().isEmpty() && !this.tf_visuel.getText().isEmpty()) {
			valide=true;
		}
		
		if(valide=true) {

			try {
				//Class.forName("com.mysql.jdbc.Driver");
				String sql="insert Categorie (titre,visuel) values(?,?)";
				Connexion con= new Connexion();
				con.creeConnexion();
				PreparedStatement ps= con.maConnexion.prepareStatement(sql);
				ps.setString(1, tf_titre.getText());
				ps.setString(2, tf_visuel.getText());

				
				int ok=ps.executeUpdate();
				
				if(ok>0) {
					System.out.println("insert Ok");
				}


			}catch (Exception e) {
				System.out.println(e);
				
			}

		}
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
		
		Stage categorie=(Stage)tf_visuel.getScene().getWindow();
		categorie.close();
		
	}
	
		

}
