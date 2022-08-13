package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controllers.Comment;
import controllers.ListTask;
import controllers.Tag;
import controllers.Task;
import controllers.User;

public class Modele {

	private static Database db = new Database("exprimons-projet.c50gqqod3ibz.eu-west-3.rds.amazonaws.com",
			"exprimonsnousprojet", "admin", "kB9qG7e3zEU3");

	// =====================================

	public static void executerRequete(String requete) {
		try {
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete);
		}
	}

	// =====================================

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
				allTasks.add(new Task(unRes.getInt("idTask"), unRes.getString("title"), unRes.getString("description"),
						unRes.getInt("idUser"), unRes.getInt("idList"), unRes.getInt("idTag")));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
		}

		return allTasks;
	}

	// =====================================================================================================

	public static void insertTask(Task task) { // RETREIVE LISTS WITHOUT TASKS

		String requete = "INSERT INTO Task VALUES (null, '" + task.getTitle() + "','" + task.getDescription() + "',"
				+ task.getIdUser() + "," + task.getIdList() + ", " + task.getIdTag() + " );";
		executerRequete(requete);
	}

	// =====================================================================================================

	public static ArrayList<Tag> getAllTags() { // RETREIVE LISTS WITHOUT TASKS

		ArrayList<Tag> allTags = new ArrayList<Tag>();
		String request = "SELECT * FROM Tag;";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			while (unRes.next()) {
				allTags.add(new Tag(unRes.getInt("idTag"), unRes.getString("name")));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
		}

		return allTags;
	}

	// =====================================================================================================

	public static Task getTask(int idTask) { // RETREIVE LISTS WITHOUT TASKS

		Task task = new Task();
		String request = "SELECT * FROM Task WHERE idTask = " + idTask + ";";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			if (unRes.next()) {
				task.setIdTask(unRes.getInt("idTask"));
				task.setTitle(unRes.getString("title"));
				task.setDescription(unRes.getString("description"));
				task.setIdUser(unRes.getInt("idUser"));
				task.setIdList(unRes.getInt("idList"));
				task.setIdTag(unRes.getInt("idTag"));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
			System.out.println(exp);
		}

		return task;
	}

	// =====================================================================================================

	public static ArrayList<Comment> getCommentFromTask(int idTask) { // RETREIVE LISTS WITHOUT TASKS

		ArrayList<Comment> comments = new ArrayList<Comment>();
		String request = "SELECT * FROM Comment WHERE idTask = " + idTask + ";";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			while (unRes.next()) {
				comments.add(new Comment(unRes.getInt("idComment"), unRes.getString("body"), unRes.getString("date"),
						unRes.getInt("idTask"), unRes.getInt("idUser")));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
			System.out.println(exp);
		}

		return comments;
	}

	// =====================================================================================================

	public static void insertComment(Comment comment) { // RETREIVE LISTS WITHOUT TASKS

		//INSERT INTO Comment VALUES (null, "C'est si dur, seigneur aidé moi", "2022-08-12", 1, 1);
		// ESCAPIG ' CHARACTERES
		String body = comment.getBody().replace("'", "\'");
		String requete = "INSERT INTO Comment VALUES (null, \""+body+"\", '"+comment.getDate()+"', "+comment.getIdTask()+", "+comment.getIdUser()+");";
		executerRequete(requete);
	}
	
	// =====================================================================================================
	
	public static int insertList(ListTask listTask) { // RETREIVE LISTS WITHOUT TASKS

		//INSERT INTO Comment VALUES (null, "C'est si dur, seigneur aidé moi", "2022-08-12", 1, 1);
		// ESCAPIG ' CHARACTERES
		String requete = "INSERT INTO List VALUES (null, \""+listTask.getTitle()+"\", "+listTask.getIdUser()+");";
		executerRequete(requete);
		
		// GETTING THE LAST idList AFTER INSERT
		
		int idList = 0;
		String request = "SELECT idList FROM List ORDER BY idList DESC LIMIT 1;";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			if (unRes.next()) {
				idList = unRes.getInt("idList");
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
			System.out.println(exp);
		}

		return idList;
	}
	
	// =====================================================================================================
	
	public static Task getLastCreatedTask() { // RETREIVE LISTS WITHOUT TASKS
		
		Task task = new Task();
		String request = "SELECT * FROM Task ORDER BY idTask DESC LIMIT 1;";

		try {
			// CONNECT TO DB
			db.seConnecter();
			Statement unStat = db.getMaConnexion().createStatement();

			// EXECUTE REQUEST
			ResultSet unRes = unStat.executeQuery(request);

			if (unRes.next()) {
				task.setIdTask(unRes.getInt("idTask"));
				task.setTitle(unRes.getString("title"));
				task.setDescription(unRes.getString("description"));
				task.setIdUser(unRes.getInt("idUser"));
				task.setIdList(unRes.getInt("idList"));
				task.setIdTag(unRes.getInt("idTag"));
			}
			unRes.close();
			unStat.close();
			db.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + request);
			System.out.println(exp);
		}

		return task;
	}
	
}
