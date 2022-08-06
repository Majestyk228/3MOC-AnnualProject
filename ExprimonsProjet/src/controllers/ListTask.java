package controllers;

import java.util.ArrayList;

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
	public ArrayList<Task> getListTask() {
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
	
	public int getIdList() {
		return idList;
	}

	public void setIdList(int idList) {
		this.idList = idList;
	}



	@Override
	public String toString() {
		return "ListTask [idList=" + idList + ", listTask=" + listTask + ", title=" + title + ", idUser=" + idUser
				+ "]";
	}
}
