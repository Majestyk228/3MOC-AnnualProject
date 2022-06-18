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


//gives all community IDs from one user
async function getUsersCommunity(id) {
	const rows = await db.query("SELECT idCommunity FROM Associate WHERE idUser = " + id + ";", "");
	return rows;
}

//gives the first 3 users with highest score from a given community
async function getThreeBestUserByCommunity(id) {
	const rows = await db.query("SELECT u.idUser, u.firstName, u.lastName FROM User u, Associate a WHERE a.idUser = u.idUser AND a.idCommunity = " + id + " ORDER BY(u.points) DESC LIMIT 3; ", "");
	return rows;
}


module.exports = {
	getCommunityInfo,
	getCommunityStats,
	getTopChoicesVotes,
	getUsersCommunity,
	getThreeBestUserByCommunity
}
