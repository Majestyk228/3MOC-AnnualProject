const db = require('./database.js');

async function getAllRewards() {
	const rows = await db.query("SELECT * FROM Rewards;", "");
	return rows;
}


async function getRewardPointsValues(idRewards) {
	const rows = await db.query("SELECT score FROM Rewards WHERE idRewards = " + idRewards + ";", "");
	return rows[0];
}


async function nbRewardByPost(idPost) {
	const request = "SELECT COUNT(*) AS rewards FROM Congratulate WHERE idPost =" + idPost + " ;";
	const rows = await db.query(request, "");
	return rows;
}


async function useReward(body) {
	const request = "INSERT INTO Congratulate(idRewards, idPost,idUser) VALUES (" + body.idRewards + "," + body.idPost + "," + body.idUser + ");";
	const rows = await db.query(request, "");
	return rows;
}

module.exports = {
	getAllRewards,
	nbRewardByPost,
	useReward,
	getRewardPointsValues
}
