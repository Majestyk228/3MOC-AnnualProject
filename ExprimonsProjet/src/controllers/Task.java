package controllers;

public class Task {

	// ATTRIBUTES
	private int idTask;
	private String title;
	private String description;
	private int idUser;
	private int idList;
	private int idTag;

	// CONSTRUCTOR
	public Task(int idTask, String title, String description, int idUser, int idList, int idTag) {
		this.idTask = idTask;
		this.title = title;
		this.description = description;
		this.idUser = idUser;
		this.idList = idList;
		this.idTag = idTag;
	}

	public Task() {

	}

	// GETTERS & SETTERS
	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getIdTag() {
		return idTag;
	}

	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", title=" + title + ", description=" + description + ", idUser=" + idUser
				+ ", idList=" + idList + ", idTag=" + idTag + "]";
	}

}
