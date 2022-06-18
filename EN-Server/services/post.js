const db = require('./database.js');

async function getAllPosts() {
	const rows = await db.query("SELECT * FROM Post;", "");
	return rows;
}


async function getAllPostsFormatted(idCommunity) {
	const request = "SELECT p.idPost, u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes, c.nbComment as comments, co.nbRewards as rewards FROM User u LEFT JOIN Post p ON " + idCommunity + " = p.idCommunity LEFT JOIN(select COUNT(c.idComment) as nbComment, idPost from Comment c) as c ON p.idPost = c.idPost left JOIN(select Count(co.idRewards) as nbRewards, idPost from Congratulate co) as co ON p.idPost = co.idPost left JOIN Associate a ON p.idCommunity = a.idCommunity where u.idUser = p.idUser group by u.firstName, u.lastName, p.title, p.body, p.likes, p.dislikes; ";
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

module.exports = {
	getAllPosts,
	getAllPostsBis,
	getPost,
	getAllPostsFormatted
}
