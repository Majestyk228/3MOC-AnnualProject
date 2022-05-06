const db = require('./database.js');

async function getCommunityInfo(id) {
	const rows = await db.query("SELECT idCommunity, label FROM Community where idCommunity=" + id + ";", "");
	return rows;
}

module.exports = {
	getCommunityInfo
}
