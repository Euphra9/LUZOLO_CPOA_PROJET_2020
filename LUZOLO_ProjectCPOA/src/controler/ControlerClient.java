package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Connexion;
import model.metier.Client;

public class ControlerClient implements Initializable{
	
	DaoFactory daoF;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);

	}
	@FXML
	private Label lbl_nom,lbl_prenom,lbl_email,lbl_mdp,lbl_adresse,lbl_code,lbl_ville,lbl_pays;
	@FXML
	private Label nom,prenom,email,mdp,code,adresse,ville,pays;
	@FXML
	private Label nom_2,prenom_2,email_2,mdp_2,code_2,num,voie,ville_2,pays_2;
	@FXML
	private TextField id_client,tf_nom,tf_prenom,tf_email,tf_adresseNum,tf_adresseVoie,tf_code,tf_ville,tf_pays;
	
	@FXML private RadioButton rb_sql,rb_listem;

	@FXML Label lbl_erreur;

	@FXML
	private TextField id_remove;
	@FXML PasswordField  tf_mdp;
	
	@FXML private TextField nom_modif,prenom_modif,email_modif,mdp_modif,code_modif,ville_modif;
	@FXML private TextField pays_modif,num_modif,voie_modif;
	
	@FXML private TextField details_id;
	
	@FXML
	private Button btn_delete;
	

	@FXML
	private TableView<Client> tblClients;
	
	public ObservableList<Client> dataCl=FXCollections.observableArrayList();
	
	
	@FXML
	TableColumn<Client, Integer> colId =
			new TableColumn<>("Identifiant");
	@FXML
	TableColumn<Client, String> colNom =
			new TableColumn<>("Nom");
	
	@FXML
	TableColumn<Client, String> colPrenom =
			new TableColumn<>("Prenoḿ");
	
	
public void resultButton(String valeur) {
	//valeur.booleanValue();
	

	
		
}
	
//------------------------------------------------------------
	public void newModelClient() {
		boolean valide=false;

		if (!this.tf_nom.getText().isEmpty() && !this.tf_prenom.getText().isEmpty() && !this.tf_email.getText().isEmpty()
				&& !this.tf_mdp.getText().isEmpty() && !this.tf_adresseNum.getText().isEmpty() && !this.tf_adresseVoie.getText().isEmpty()
				&& !this.tf_code.getText().isEmpty() && !this.tf_ville.getText().isEmpty() && !this.tf_pays.getText().isEmpty() )
		{
			if(rb_sql.isSelected()) {
				Connexion con= new Connexion();

				
				try {
					con.creeConnexion();
					PreparedStatement ps= con.createClientStatement();
					ps.setString(1, tf_nom.getText());
					ps.setString(2, tf_prenom.getText());
					ps.setString(3, tf_email.getText());
					ps.setString(4, tf_mdp.getText());
					ps.setString(5, tf_adresseNum.getText());
					ps.setString(6, tf_adresseVoie.getText());
					ps.setString(7, tf_code.getText());
					ps.setString(8, tf_ville.getText());
					ps.setString(9, tf_pays.getText());
					int ok=ps.executeUpdate();
					
					if(ok>0) {
						System.out.println("insert Ok");
						tf_nom.clear();
						tf_prenom.clear();
						tf_email.clear();
						tf_mdp.clear();
						tf_adresseNum.clear();
						tf_adresseVoie.clear();
						tf_code.clear();
						tf_ville.clear();
						tf_pays.clear();
						
						this.lbl_erreur.setTextFill(Color.GREEN);
						this.lbl_erreur.setText("Un client a été ajouté dans la base des données");
						
						
						
						
					}


				}catch (Exception e) {
					System.out.println(e);
				}
				
				
					
				}
			
			else if(rb_listem.isSelected()) {
				
				if(valide=true) {
				colId.setCellValueFactory(
						new PropertyValueFactory<Client, Integer>("Id"));
				colNom.setCellValueFactory(
						new PropertyValueFactory<Client, String>("Nom"));
				colPrenom.setCellValueFactory(
						new PropertyValueFactory<Client, String>("Prenom"));
			
				dataCl.add(new Client(tblClients.hashCode(),tf_nom.getText(),tf_prenom.getText()));
				tblClients.setItems(dataCl);
				
				tf_nom.clear();
				tf_prenom.clear();
				tf_email.clear();
				tf_mdp.clear();
				tf_adresseNum.clear();
				tf_adresseVoie.clear();
				tf_code.clear();
				tf_ville.clear();
				tf_pays.clear();
				
				this.lbl_erreur.setTextFill(Color.GREEN);
				this.lbl_erreur.setText("Un client a été ajouté dans la liste mémoire");
				
				
				
				}
				
			}
			
			else if(rb_listem.isSelected()&&rb_sql.isSelected()) {
				this.lbl_erreur.setTextFill(Color.RED);
				this.lbl_erreur.setText("Vous ne pouvez pas enregistrer en Listeme memoire et SQL à la fois !");
			}
			
			else {
				
				this.lbl_erreur.setTextFill(Color.RED);
				this.lbl_erreur.setText("Veillez choisir un emplacement d'enregistrement");
				}
				
		}
		
		else {
			this.lbl_erreur.setTextFill(Color.RED);
			this.lbl_erreur.setText("Des champs sont vides ou mal remplis !");

		}
	}

	
	//---------------
	
	@FXML
	public void viewModelClient() {
		
		if(rb_sql.isSelected()) {
		try {
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.viewClientStatement();
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				dataCl.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}


		}catch (Exception e) {
			System.out.println(e);
			
		}
		
		colId.setCellValueFactory(
				new PropertyValueFactory<Client, Integer>("Id"));
		colNom.setCellValueFactory(
				new PropertyValueFactory<Client, String>("Nom"));
		colPrenom.setCellValueFactory(
				new PropertyValueFactory<Client, String>("Prenom"));
		tblClients.setItems(dataCl);
		}
		
		else if(rb_listem.isSelected()) {
			this.tblClients.getItems().addAll( DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoClient().findAll());
		}
		
		
		
	}
	
public void updateModelClient() {
		
		try {
			String sql="UPDATE Client SET nom=?, prenom=?, identifiant=?, mot_de_passe=?,"
					+ "adr_numero=?,adr_voie=?,adr_code_postal=?,adr_ville=?,adr_pays=?"
					+ " WHERE id_client="+id_client.getText();
			
			
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			ps.setString(1, nom_modif.getText());
			ps.setString(2, prenom_modif.getText());
			ps.setString(3, email_modif.getText());
			ps.setString(4, mdp_modif.getText());
			ps.setString(5, num_modif.getText());
			ps.setString(6, voie_modif.getText());
			ps.setString(7, code_modif.getText());
			ps.setString(8, ville_modif.getText());
			ps.setString(9, pays_modif.getText());
			
			ps.executeUpdate();


			this.lbl_erreur.setTextFill(Color.GREEN);
			this.lbl_erreur.setText("Modification effectuée");

		}catch (Exception e) {
			System.out.println(e);
			
		}
	}

public void detailsModelClient() {
	try {
		
		String sql="SELECT nom,prenom,identifiant, mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,"
				+ "adr_pays FROM Client WHERE id_client="+details_id.getText();
		
		Connexion con= new Connexion();
		con.creeConnexion();
		PreparedStatement ps= con.maConnexion.prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String cl1=rs.getString(1);
			String cl2=rs.getString(2);
			String cl3=rs.getString(3);
			String cl4=rs.getString(4);
			String cl5=rs.getString(5);
			String cl6=rs.getString(6);
			String cl7=rs.getString(7);
			String cl8=rs.getString(8);
			String cl9=rs.getString(9);


			nom.setText("Nom");
			prenom.setText("Preom");
			email.setText("Adresse mail");
			mdp.setText("Mot de passe");
			adresse.setText("Adresse");
			code.setText("Code Postal");
			ville.setText("Ville");
			pays.setText("Pays");


			nom_2.setText(cl1.toString());
			prenom_2.setText(cl2.toString());
			email_2.setText(cl3.toString());
			mdp_2.setText(cl4.toString());
			num.setText(cl5.toString());
			voie.setText(cl6.toString());
			code_2.setText(cl7.toString());
			ville_2.setText(cl8.toString());
			pays_2.setText(cl9.toString());




			
			
			
		System.out.println(cl1);
		
		}
		System.out.println("cliqué");
		//String nom_modif=rs.getString(1).toString();
		

		//System.out.println("prod modifié");


	}catch (Exception e) {
		System.out.println(e);
		
	}
}
	
//-----------------------------------------------------------
	@FXML
	public  void viewDelete() {
		
		
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/removeClient.fxml"));

		
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
		
		
		
	}
	@FXML
	public  void viewDetails() {
		
		
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewClient.fxml"));

		
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
		
		
		
	}
//------------------------------------
	@FXML
	public void deleteModelClient() {
		try {
			String sql="DELETE FROM Client WHERE id_client="+id_remove.getText();
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			ps.executeUpdate();

			this.lbl_erreur.setTextFill(Color.RED);
			this.lbl_erreur.setText("Le client avec l'identifiant "+id_remove.getText() +" a été supprimé");

		}catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	

	
	@FXML
	public void viewUpdate() {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/updateClient.fxml"));

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
		
		Stage client=(Stage)tf_nom.getScene().getWindow();
		client.close();
		
	}
	
	public void updateModelClient_2() {
		try {
			
			String sql="SELECT nom,prenom,identifiant, mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,"
					+ "adr_pays FROM Client WHERE id_client="+id_client.getText();
			
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String cl1=rs.getString(1);
				String cl2=rs.getString(2);
				String cl3=rs.getString(3);
				String cl4=rs.getString(4);
				String cl5=rs.getString(5);
				String cl6=rs.getString(6);
				String cl7=rs.getString(7);
				String cl8=rs.getString(8);
				String cl9=rs.getString(9);


				nom_modif.setText(cl1.toString());
				prenom_modif.setText(cl2.toString());
				email_modif.setText(cl3.toString());
				mdp_modif.setText(cl4.toString());
				num_modif.setText(cl5.toString());
				voie_modif.setText(cl6.toString());
				code_modif.setText(cl7.toString());
				ville_modif.setText(cl8.toString());
				pays_modif.setText(cl9.toString());




				
				
				
			System.out.println(cl1);
			
			}
			System.out.println("cliqué");
			//String nom_modif=rs.getString(1).toString();
			

			//System.out.println("prod modifié");


		}catch (Exception e) {
			System.out.println(e);
			
		}
	}
		
	
	
	//--------------------------------------------------------
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
		
		Stage client=(Stage)tf_nom.getScene().getWindow();
		client.close();
		
	}
	
	@FXML
	public void revenirUpdate() {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/clients.fxml"));

		
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
		
		Stage client=(Stage)nom_modif.getScene().getWindow();
		client.close();
		
	}
	
	@FXML
	public void revenirRemove() {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/clients.fxml"));

		
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
		
		Stage client=(Stage)id_remove.getScene().getWindow();
		client.close();
		
	}
	
	@FXML
	public void revenirView() {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/clients.fxml"));

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
		
		Stage client=(Stage)nom.getScene().getWindow();
		client.close();
		
	}
		
	
	
}
