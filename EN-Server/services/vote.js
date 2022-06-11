const db = require('./database.js');


async function getVoteListByCommunity(idCommunity) {
	const rows = await db.query("SELECT idVote, title FROM Vote WHERE idCommunity = " + idCommunity + "ORDER BY voteBegins;", "");
	return rows;
}

module.exports = {
	getVoteListByCommunity
}
