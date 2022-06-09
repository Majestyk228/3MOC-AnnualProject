const db = require('./database.js');

async function getCommunityInfo(id) {
	const rows = await db.query("SELECT idCommunity, label FROM Community where idCommunity=" + id + ";", "");
	return rows;
}

async function getCommunityStats(id) {
	const rows = await db.query("SELECT COUNT(a.idUser) as nbUsers, SUM(u.points) as totalPointsCommunity, COUNT(idPost) as nbPost, COUNT(idVote) as nbVote FROM User u, Associate a, Post p, Vote v WHERE a.idCommunity = " + id + " AND u.idUser = a.idUser AND p.idCommunity = a.idCommunity AND v.idCommunity = a.idCommunity;", "");
	return rows;
}

//vote top choices
async function getTopChoicesVotes(id) {
	const rows = await db.query("SELECT v.idVote, v.title, vo.idVoteOptions as bestChoice, v.body FROM Vote v, VoteOptions vo WHERE v.idCommunity = " + id + " GROUP BY idVoteOptions ORDER BY Count(*) DESC LIMIT 1;", "");
	return rows;
}

module.exports = {
	getCommunityInfo,
	getCommunityStats,
	getTopChoicesVotes
}
