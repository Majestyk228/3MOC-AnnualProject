const db = require('./database.js');

async function getAllPosts() {
	const rows = await db.query("SELECT * FROM Post;", "");
	return rows;
}

async function getPost(id) {
	const rows = await db.query("SELECT * FROM Post WHERE idPost=" + id + ";", "");
	return rows;
}

module.exports = {
	getAllPosts,
	getPost
}
