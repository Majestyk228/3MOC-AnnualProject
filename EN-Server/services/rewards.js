const db = require('./database.js');

async function getAllRewards() {
	const rows = await db.query("SELECT * FROM Rewards;", "");
	return rows;
}


async function nbRewardByPost(idPost) {
	const request = "SELECT COUNT(*) AS rewards FROM Congratulate WHERE idPost =" + idPost + " ;";
	const rows = await db.query(request, "");
	return rows;
}


module.exports = {
	getAllRewards,
	nbRewardByPost
}