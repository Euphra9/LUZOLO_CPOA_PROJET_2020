package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import dao.factory.DaoFactory;
import dao.factory.DaoFactory.Persistance;
import dao.listememoire.ListeMemoireProduitDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Connexion;
import model.metier.Categorie;
import model.metier.Client;
import model.metier.Produit;


public class ControlerProduit implements Initializable {
	DaoFactory daoF;
	ListeMemoireProduitDAO produit;
	
	@FXML
	private TextField tf_nom,tf_tarif,tf_visuel,id_produit;
	
	@FXML private TextField id_prod,nom_modif,visuel_modif,tarif_modif;
	
	@FXML Label lbl_erreur;
	@FXML
	private TextArea ta_description,descr_modif;
	
	@FXML private RadioButton rb_sql,rb_listem;
	
	@FXML
	private ChoiceBox<Categorie> cbx_categorie,categ_modif;
	
	@FXML
	private TableView<Produit> tblProduits;

	
	
	public ObservableList<Produit> dataPr=FXCollections.observableArrayList();
	
	public ObservableList<Categorie> dataCateg=FXCollections.observableArrayList();
	
	private List<Produit> donnees;


	
	@FXML
	TableColumn<Produit, Integer> colId =
			new TableColumn<>("Identifiant");
	@FXML
	TableColumn<Produit, String> colNom =
			new TableColumn<>("Nom");
	
	@FXML
	TableColumn<Produit, String> colDescr =
			new TableColumn<>("Description");
	@FXML
	TableColumn<Produit, Double> colTarif =
			new TableColumn<>("Tarif");
	@FXML
	TableColumn<Produit, String> colVisuel =
			new TableColumn<>("Visuel");
	@FXML
	TableColumn<Produit, Integer> colCateg =
			new TableColumn<>("Categorie");
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		daoF = DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE);
		Connexion con= new Connexion();		
		

		/*try {
			String sql="SELECT titre FROM Categorie";
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				dataCateg.add(new Categorie(0, rs.getString(1), sql));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rb_listem.isSelected()) {
			this.cbx_categorie.setItems(FXCollections.observableArrayList(daoF.getDaoCategorie().findAll()));
			
		}
		else if (rb_sql.isSelected()) {
			this.cbx_categorie.setItems(dataCateg);

		}*/
		
		this.cbx_categorie.setItems(FXCollections.observableArrayList(daoF.getDaoCategorie().findAll()));

		

		
		
		con.Deconnection();
		

		
		
	}
	
	//--------------------------------------------------------------------------------
	
	//---------------------AJOUTER UN PRODUIT -----------------------
	@FXML
	public void newModelProduit() {
		
		boolean valide = false;
		
		if (!this.tf_nom.getText().isEmpty() && !this.ta_description.getText().isEmpty()
				&& !this.tf_tarif.getText().isEmpty() 	&& !this.tf_visuel.getText().isEmpty()
				&& this.cbx_categorie.getSelectionModel().getSelectedIndex() != -1) {
			valide = true;
			System.out.println("ok");
		}
		
		else {
			this.lbl_erreur.setTextFill(Color.RED);
			this.lbl_erreur.setText("Des champs sont vides ou mal remplis !");
		}

		if(rb_sql.isSelected()) {
		Connexion con= new Connexion();

		

		if (valide=true) {

			
			try {
				
				String sql="insert into Produit (nom,description,tarif,"
						+ " visuel,id_categorie) values(?,?,?,?,?)";
				//Connexion con= new Connexion();
				con.creeConnexion();
				PreparedStatement ps= con.maConnexion.prepareStatement(sql);
				
				ps.setString(1, tf_nom.getText());
				ps.setString(2, ta_description.getText());
				ps.setString(3, tf_tarif.getText());
				ps.setString(4, tf_visuel.getText());
				//ps.setString(6, cbx_categorie.getValue());
				ps.setInt(5, 2);
				
				int ok=ps.executeUpdate();
				
				if(ok>0) {
					System.out.println("insert Ok");
					tf_nom.clear();
				}


			}catch (Exception e) {
				System.out.println(e);
				
			}

		} else {
			
			this.lbl_erreur.setTextFill(Color.RED);
			this.lbl_erreur.setText("Insertion échouée à la base des données!");

			
		}
		
		con.Deconnection();
		}
		
		else if(rb_listem.isSelected())
		{
			
			this.lbl_erreur.setText(" ");
			if (valide=true) {
			
			colId.setCellValueFactory(
					new PropertyValueFactory<Produit, Integer>("Id"));
			
			colNom.setCellValueFactory(
					new PropertyValueFactory<Produit, String>("Nom"));
			
			colDescr.setCellValueFactory(
					new PropertyValueFactory<Produit, String>("Description"));
			
			colTarif.setCellValueFactory(
					new PropertyValueFactory<Produit, Double>("Prenom"));
			
			colVisuel.setCellValueFactory(
					new PropertyValueFactory<Produit, String>("Visuel"));
			
			colCateg.setCellValueFactory(
					new PropertyValueFactory<Produit, Integer>("Categorie"));
			
			System.out.println("Liste memoire");
			
			dataPr.add(new Produit(tblProduits.hashCode(), tf_nom.getText(), ta_description.getText(), 3.2, tf_visuel.getText(), 0));
			tblProduits.setItems(dataPr);
			System.out.println(dataPr);
			tf_nom.clear();
			ta_description.clear();
			tf_tarif.clear();
			tf_visuel.clear();
			

			}
			
			
			else {
				this.lbl_erreur.setTextFill(Color.RED);
				this.lbl_erreur.setText("Insertion échouée à la liste memoire!");
			}

		}
		
		else {
			this.lbl_erreur.setTextFill(Color.RED);
			this.lbl_erreur.setText("Veillez selectionner un enregistrement !");
		}
			

	}
	
	
	
	
	//-------------------SUPPRIMER LE PRODUIT--------------------------------
	@FXML
	public void deleteModelProduit() {
		try {
			String sql="DELETE FROM Produit WHERE id_produit="+id_produit.getText();
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);
			ps.executeUpdate();

			System.out.println("prod supprimé");


		}catch (Exception e) {
			System.out.println(e);
			
		}
	}
	//----------------------------------------------------------------------------------
	//------------------------AFFICHER LES DONNÉES DANS LE TABLEAU-------------------------------
	@FXML
	public void viewModelProduit() {
		
		if(rb_sql.isSelected()) {
		try {
			String sql="SELECT id_produit,nom,description,tarif,visuel,id_categorie FROM Produit";
			Connexion con= new Connexion();
			con.creeConnexion();
			PreparedStatement ps= con.maConnexion.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				dataPr.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5), rs.getInt(6)));
			}


		}catch (Exception e) {
			System.out.println(e);
			
		}
		
		colId.setCellValueFactory(
				new PropertyValueFactory<Produit, Integer>("Id"));
		
		colNom.setCellValueFactory(
				new PropertyValueFactory<Produit, String>("Nom"));
		
		colDescr.setCellValueFactory(
				new PropertyValueFactory<Produit, String>("Description"));
		
		colTarif.setCellValueFactory(
				new PropertyValueFactory<Produit, Double>("Prenom"));
		
		colVisuel.setCellValueFactory(
				new PropertyValueFactory<Produit, String>("Visuel"));
		
		colCateg.setCellValueFactory(
				new PropertyValueFactory<Produit, Integer>("Categorie"));
		
		//Pour MySQL
		
		tblProduits.setItems(dataPr);
		}
		
		else if(rb_listem.isSelected()) {
		
		
		//Pour la liste mémoire
		this.tblProduits.getItems().addAll( DaoFactory.getDaoFactory(Persistance.LISTEMEMOIRE).getDaoProduit().findAll());
		System.out.println(dataPr);
		}
		
		else {
			
		}
	}
	

	//------------------------------------------------------------------------------------
	//------------------------AFFICHAGE VUE POUR LA MODIFICATION DU PRODUIT----------------------
	@FXML
	public void viewUpdateProduit() {
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/updateProduit.fxml"));

		
		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage delete_produit=new Stage();
			
			delete_produit.setScene(scene);
			delete_produit.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}
	//-------------------------AFFICHER VUE POUR LA SUPPRESSION D'UN PRODUIT---------------------
	@FXML
	public  void viewDeleteProduit() {
		
		
FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/removeProduit.fxml"));

		
		try {
			Node root=loader.load();
			
			Scene scene = new Scene((VBox)root,600,800);

			
			Stage delete_produit=new Stage();
			
			delete_produit.setScene(scene);
			delete_produit.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	//----------------------------- RETOUR A L'ACCUEIL -----------------------------------
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
		
		Stage produit=(Stage) tf_nom.getScene().getWindow();
		produit.close();
		
	}

}
