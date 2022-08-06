package controllers;

import java.util.ArrayList;
import java.util.List;

public class ListTask {

	// ATTRIBUTES
	private int idList;
	private ArrayList<Task> listTask;
	private String title;
	private int idUser;
	
	

	// CONSTRUCTOR
	public ListTask(int idList, String title, int idUser) {
		this.idList = idList;
		this.title = title;
		this.idUser = idUser;
	}
	
	

	// GETTER & SETTER
	public List getListTask() {
		return listTask;
	}

	public void setListTask(ArrayList<Task> listTask) {
		this.listTask = listTask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "ListTask [idList=" + idList + ", listTask=" + listTask + ", title=" + title + ", idUser=" + idUser
				+ "]";
	}
}
