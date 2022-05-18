const db = require('./database.js');

async function getAllRewards() {
	const rows = await db.query("SELECT * FROM Rewards;", "");
	return rows;
}


module.exports = {
	getAllRewards
}
