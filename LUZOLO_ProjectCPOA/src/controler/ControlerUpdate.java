package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Connexion;

public class ControlerUpdate implements Initializable {

	@FXML private TextField id_prod,nom_modif,visuel_modif,tarif_modif;
	@FXML
	private TextArea ta_description,descr_modif;

public void updateModelProduit() {
		
		try {
			String sql="UPDATE produit SET nom=?, description=?, tarif=?, visuel=?\"\n" + 
					"					+ \" WHERE id_produit=?"+id_prod.getText();
			
			
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			System.out.print("ici");

			ps.setString(1, nom_modif.getText());
			ps.setString(2, descr_modif.getText());
			ps.setString(3, tarif_modif.getText());
			System.out.print("ici");
			ps.setString(4, visuel_modif.getText());
			ps.executeUpdate();

			System.out.println("prod modifié");


		}catch (Exception e) {
			System.out.println(e);
			
		}
	}

public void updateModelProduit_2() {
	try {
		
		String sql="SELECT nom,description,tarif,visuel FROM Produit"
				+ " WHERE id_produit="+id_prod.getText();
		
		Connexion con= new Connexion();
		con.creeConnexion();
		PreparedStatement ps= con.maConnexion.prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String p1=rs.getString(1);
			String p2=rs.getString(2);
			Double p3=rs.getDouble(3);
			String p4=rs.getString(4);

			
			nom_modif.setText(p1.toString());
			descr_modif.setText(p2.toString());
			//tarif_modif.setText(String.valueOf(p3));
			tarif_modif.setText(Double.toString(p3));
			visuel_modif.setText(p4.toString());

			
		System.out.println(p1);
		System.out.println(rs.getString(2));
		System.out.println(rs.getDouble(3));
		System.out.println(rs.getString(4));
		}
		System.out.println("cliqué");
		//String nom_modif=rs.getString(1).toString();
		

		//System.out.println("prod modifié");


	}catch (Exception e) {
		System.out.println(e);
		
	}
}

public void revenir() {
	FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/produits.fxml"));

	
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
	
	Stage produit=(Stage) nom_modif.getScene().getWindow();
	produit.close();
	

}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}
	
}
