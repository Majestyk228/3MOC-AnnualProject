const db = require('./database.js');


//getting current date and time
var today = new Date();
//var nextDay = new Date();
var time = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();
today = yyyy + '-' + mm + '-' + dd;
var Datetime = (today + ' ' + time);

async function getnbComment() {
	const rows = await db.query("SELECT Count(idComment) as nbComment FROM Comment;", "");
	return rows;
}

async function getReportedComments(idPost) {
	const rows = await db.query("SELECT com.* FROM Comment com, Post p, User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser AND reports>0 AND com.idPost = " + idPost + ";", "");
	return rows;
}


async function getNbCommentsPerPost(idPost) {
	const rows = await db.query("SELECT Count(com.idComment) as nbComment FROM Comment com, Post p,  User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser AND com.idPost = " + idPost + ";", "");
	return rows;
}

async function getNbReportedCommentsPerPost(idPost) {

	const rows = await db.query("SELECT Count(com.idComment) as nbReportedComment FROM Comment com, Post p,  User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser  AND com.reports>0 AND com.idPost = " + idPost + ";", "");
	return rows;
}


async function getNbReportedComments() {
	const rows = await db.query("SELECT Count(com.idComment) as nbReportedComment FROM Comment com, Post p, User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser AND com.reports>0;", "");
	return rows;
}




async function getComments(idPost) {
	const rows = await db.query("SELECT com.* FROM Comment com, Post p, User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser AND com.idPost = " + idPost + ";", "");
	return rows;
}




async function insertComment(commentReq) {
	const requete = "INSERT INTO Comment(idComment, body, likes, dislikes, reports, anonymous, idPost, idUser, date) VALUES (null, \"" + commentReq.body + "\", 0,0,0, " + commentReq.anonymous + ", " + commentReq.idPost + ", " + commentReq.idUser + ",'" + today + "');";
	//const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + userReq.password + "', 0);";
	const results = await db.query(requete, "");

	//message to output at the end of the function
	let message = 'Error in creating contact';

	if (results.affectedRows) {
		message = 'Contact created successfully';
	}

	return { message };
}


async function getnbReportedCommentsAll(idCommunity) {
	// TODO code
	const request = "SELECT COUNT(*) AS nbReportedComments FROM Comment c, Post p WHERE c.reports>0 AND p.idCommunity = " + idCommunity + " AND c.idPost = p.idPost;";

	const rows = await db.query(request, "");
	return rows;
}


async function deleteComment(idComment) {
	const request = "DELETE FROM Comment WHERE idComment=" + idComment + "";
	const rows = await db.query(request, "");
	return rows;
}

async function nbCommentAndroidPost(idPost) {
	const request = "SELECT COUNT(*) as nbComment FROM Congratulate WHERE idPost = " + idPost + ";";
	const rows = await db.query(request, "");
	return rows[0];
}


async function reportComment(idComment) {
	const request = "UPDATE Comment SET reports = reports +1 WHERE idComment = " + idComment + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function reportCommentReinit(idComment) {
	const request = "UPDATE Comment SET reports = 0 WHERE idComment = " + idComment + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function commmentByPost(idPost) {
	const request = "SELECT u.firstName, u.lastName, c.body,c.idComment, c.anonymous FROM User u, Comment c WHERE c.idPost = " + idPost + " GROUP BY idComment;";
	const rows = await db.query(request, "");
	return rows;
}


async function commmentByCommunity(idCommunity) {
	const request = "SELECT * FROM Comment c, Post p WHERE p.idPost = c.idPost AND p.idCommunity = " + idCommunity + ";";
	const rows = await db.query(request, "");
	return rows;
}



async function reportedCommmentByCommunity(idCommunity) {
	const request = "SELECT * FROM Comment c, Post p WHERE p.idPost = c.idPost AND c.reports > 0 AND p.idCommunity = " + idCommunity + ";";
	const rows = await db.query(request, "");
	return rows;
}

module.exports = {
	getnbComment,
	getReportedComments,
	getNbCommentsPerPost,
	getNbReportedCommentsPerPost,
	getNbReportedComments,
	getComments,
	insertComment,
	getnbReportedCommentsAll,
	deleteComment,
	nbCommentAndroidPost,
	reportComment,
	reportCommentReinit,
	commmentByPost,
	reportedCommmentByCommunity,
	commmentByCommunity
}
