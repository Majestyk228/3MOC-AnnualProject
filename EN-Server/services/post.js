const db = require('./database.js');

async function getAllPosts() {
	const rows = await db.query("SELECT * FROM Post;", "");
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
	getPost
}
