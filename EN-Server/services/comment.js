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
	const requete = "INSERT INTO Comment(idComment, body, likes, dislikes, reports, anonymous, idPost, idUser, date) VALUES (null, '" + commentReq.body + "', 0,0,0, " + commentReq.anonymous + ", " + commentReq.idPost + ", " + commentReq.idUser + ",'" + today + "');";
	//const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + userReq.password + "', 0);";
	console.log("\n\n" + commentReq.anonymous);
	const results = await db.query(requete, "");

	//message to output at the end of the function
	let message = 'Error in creating contact';

	if (results.affectedRows) {
		message = 'Contact created successfully';
	}

	return { message };
}

module.exports = {
	getnbComment,
	getReportedComments,
	getNbCommentsPerPost,
	getNbReportedCommentsPerPost,
	getNbReportedComments,
	getComments,
	insertComment
}
