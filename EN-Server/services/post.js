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

async function getAllPosts() {
	const rows = await db.query("SELECT * FROM Post;", "");
	return rows;
}


async function getAllPostsFormatted(idCommunity) {
	//const request = "SELECT p.idPost, u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes, c.nbComment as comments, co.nbRewards as rewards FROM User u LEFT JOIN Post p ON " + idCommunity + " = p.idCommunity LEFT JOIN(select COUNT(c.idComment) as nbComment, idPost from Comment c) as c ON p.idPost = c.idPost left JOIN(select Count(co.idRewards) as nbRewards, idPost from Congratulate co) as co ON p.idPost = co.idPost left JOIN Associate a ON p.idCommunity = a.idCommunity where u.idUser = p.idUser group by u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes; ";
	const request = "SELECT p.idPost, u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes FROM User u, Post p WHERE u.idUser = p.idUser AND p.idCommunity = " + idCommunity + " group by u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes; ";
	const rows = await db.query(request, "");
	return rows;
}


async function getAllPostsBis() {
	const rows = await db.query("SELECT u.firstName as firstName, u.lastName as lastName, p.title as title, p.body as body, p.likes as likes, p.dislikes as dislikes, COUNT(com.idComment) as nbComment, COUNT(c.idPost) as nbReward FROM User u, Post p, Comment com, Congratulate c WHERE com.idPost = p.idPost AND c.idPost = p.idPost AND p.idUser = u.idUser;", "");
	return rows;
}

async function getPost(id) {
	const rows = await db.query("SELECT * FROM Post WHERE idPost=" + id + ";", "");
	return rows;
}


async function nbReportedPosts(idCommunity) {
	const rows = await db.query("SELECT COUNT(*) as nbReportedPost FROM Post WHERE reported>0 AND idCommunity = " + idCommunity + ";", "");
	return rows;
}




async function createPost(body) {

	const request = "INSERT INTO Post(idPost, title, body, date, time, likes, dislikes, idCommunity, idUser, idAdmin, reported) VALUES (null,'" + body.title + "', '" + body.body + "', '" + today + "','" + time + "',0,0," + body.idCommunity + "," + body.idUser + "," + body.idAdmin + ",0);";
	const rows = await db.query(request, "");
	return rows;
}

async function getLastPostedPosts(idCommunity) {
	const request = "SELECT idPost, title FROM Post WHERE idCommunity = " + idCommunity + " ORDER BY idPost DESC LIMIT 5;";
	const rows = await db.query(request, "");
	return rows;
}


async function getAllReportedPosts(idCommunity) {
	const request = "SELECT * FROM Post WHERE idCommunity = " + idCommunity + " AND reported > 0;";
	console.log(request);
	const rows = await db.query(request, "");
	return rows;
}



async function getAllPostsByCommunity(idCommunity) {
	const request = "SELECT * FROM Post WHERE idCommunity = " + idCommunity + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function updatePost(idPost, title, body) {
	const request = "UPDATE Post SET title = '" + title + "', body = '" + body + "' WHERE idPost = " + idPost + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function deletePost(idPost) {
	const request = "DELETE FROM Post WHERE idPost = " + idPost + ";";
	const rows = await db.query(request, "");
	return rows;
}



async function likePost(idPost) {
	const request = "UPDATE Post SET likes = likes + 1 WHERE idPost = " + idPost + ";";
	const rows = await db.query(request, "");
	return rows;
}


async function dislikePost(idPost) {
	const request = "UPDATE Post SET dislikes = dislikes + 1 WHERE idPost = " + idPost + ";";
	const rows = await db.query(request, "");
	return rows;
}
module.exports = {
	getAllPosts,
	getAllPostsBis,
	getPost,
	getAllPostsFormatted,
	nbReportedPosts,
	createPost,
	getLastPostedPosts,
	getAllReportedPosts,
	getAllPostsByCommunity,
	updatePost,
	deletePost,
	likePost,
	dislikePost
}
