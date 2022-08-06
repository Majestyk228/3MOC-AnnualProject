package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controllers.ListTask;
import controllers.Task;
import controllers.User;

public class Modele {

	private static Database db = new Database("exprimons-projet.c50gqqod3ibz.eu-west-3.rds.amazonaws.com",
			"exprimonsnousprojet", "admin", "kB9qG7e3zEU3");

	public static User verifConnexion(String email, String password) {
		User unUser = null;
		String request = "select * from User where email = '" + email + "'  and password ='" + password + "';";
		try {
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(request);

			if (unRes.next()) {
				unUser = new User(unRes.getInt("idUser"), unRes.getString("firstname"), unRes.getString("lastname"),
						unRes.getString("email"));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
			// System.out.println(exp);
		}

		return unUser;
	}

	// =====================================================================================================

	public static ArrayList<ListTask> getUsersTaskList(int idUser) { // RETREIVE LISTS WITHOUT TASKS

		ArrayList<ListTask> allLists = new ArrayList<ListTask>();
		String request = "SELECT * FROM List WHERE idUser = " + idUser + ";";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			while (unRes.next()) {
				allLists.add(new ListTask(unRes.getInt("idList"), unRes.getString("title"), idUser));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
		}

		return allLists;

	}
	
	
	
	// =====================================================================================================

		public static ArrayList<Task> getTasksFromList(int idList) { // RETREIVE LISTS WITHOUT TASKS

			ArrayList<Task> allTasks = new ArrayList<Task>();
			String request = "SELECT * FROM Task WHERE idList = " + idList + ";";

			try {
				// CONNECT TO DB
				db.seConnecter();
				Statement unStat = db.getMaConnexion().createStatement();

				// EXECUTE REQUEST
				ResultSet unRes = unStat.executeQuery(request);

				while (unRes.next()) {
					allTasks.add(new Task(unRes.getInt("idTask"), unRes.getString("title"), unRes.getString("description"), unRes.getInt("idUser"), unRes.getInt("idList"), unRes.getInt("idTag")));
				}
				unRes.close();
				unStat.close();
				db.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur d'exécution de la requete : " + request);
			}

			return allTasks;
		}
}
