package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private String serveur, bdd, user, mdp;
	private Connection maConnexion;
	
	public Database (String serveur, String bdd, String user, String mdp) {
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
	} 
	
	public void chargerPilote ()
	{
		try {
			//Vérifier la présence du pilote 
			Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Le pilote JDBC n'existe pas");
		}
	}
	
	public void seConnecter() {
		this.chargerPilote(); 
		String url = "jdbc:mysql://" + this.serveur + "/" + this.bdd+"?autoReconnect=true&useSSL=false" ; 
		try
		{
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur de connexion à url : " + url);
		}
	}
	public void seDeconnecter ()
	{
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		}
		catch (SQLException exp)
		{	
			System.out.println("Erreur lors de la fermeture de la connexion ");
			//exp.printStackTrace();
		}
	}
	public Connection getMaConnexion ()
	{
		return this.maConnexion; 
	}

}
