const db = require('./database.js');


async function getVoteListByCommunity(idCommunity) {
	const rows = await db.query("SELECT idVote, title FROM Vote WHERE idCommunity = " + idCommunity + "ORDER BY voteBegins;", "");
	return rows;
}

// TODO FUNCTIONS TO BUILD VOTE TEMPLATE
// title, body from vote
async function getVoteTitleBody(idVote) {
	const rows = await db.query("SELECT v.title, v.body FROM Vote v WHERE v.idVote = " + idVote + ";", "");
	return rows;
}

// label from voteOptions
async function getVoteOptions(idVote) {
	const rows = await db.query("SELECT vo.label, vo.idVoteOptions FROM VoteOptions vo WHERE vo.idVote = " + idVote + ";", "");
	return rows;
}


//nbChoice
async function getNbChoices(idVote, choice) {
	const query = "SELECT COUNT(vs.choice) AS nbChoice FROM Votes vs, VoteOptions vo WHERE vs.idVote = " + idVote + " AND vs.choice = " + choice + " AND vs.choice = vo.idVoteOptions;";

	//console.log(query)
	const rows = await db.query(query, "");
	return rows;
}


async function getListOfNumber(idVote, choice) {

	var number = 0;

	number = await getNbChoices(idVote, choice['idVoteOptions'])

	return (number[0])
}






module.exports = {
	getVoteListByCommunity,
	getVoteTitleBody,
	getVoteOptions,
	getNbChoices,
	getListOfNumber
}
