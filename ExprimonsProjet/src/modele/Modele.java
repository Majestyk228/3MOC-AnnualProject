package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.User;

public class Modele {
	
	private static Database db = new Database("exprimons-projet.c50gqqod3ibz.eu-west-3.rds.amazonaws.com","exprimonsnousprojet","admin","kB9qG7e3zEU3");
	
	public static User verifConnexion (String email, String password) 
	{
		User unUser = null; 
		String requete = "select * from User where email = '" + email +"'  and password ='" + password + "';";
		try {
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete); 
			
			if (unRes.next()) {
				unUser = new User (
						unRes.getInt("idUser"), unRes.getString("firstname"), unRes.getString("lastname"), 
						unRes.getString("email"));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
			//System.out.println(exp);
		}
		
		return unUser; 
	}
}
