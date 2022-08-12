package controllers;

public class Comment {
	
	// ATTRIBUTES
	private int idComment;
	private String body;
	private String date;
	private int idTask;
	private int idUser;
	
	// CONSTRUCTOR
	public Comment (int idComment, String body, String date, int idTask, int idUser) {
		
		this.idComment = idComment;
		this.body = body;
		this.date = date;
		this.idTask = idTask;
		this.idUser = idUser;
		
	}

	// GETTERS & SETTERS
	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", body=" + body + ", date=" + date + ", idTask=" + idTask
				+ ", idUser=" + idUser + "]";
	}
}
