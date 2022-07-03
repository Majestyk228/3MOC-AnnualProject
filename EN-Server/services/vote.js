const { request } = require('express');
const db = require('./database.js');

//getting current date and time
var today = new Date();
//var nextDay = new Date();
var nextDay = new Date(today.getTime() + (7 * 24 * 60 * 60 * 1000));
var time = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
var dd = String(today.getDate()).padStart(2, '0');
var dd7 = String(nextDay.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();
today = yyyy + '-' + mm + '-' + dd;
nextDay = yyyy + '-' + mm + '-' + dd7;
var Datetime = (today + ' ' + time);


async function getVoteListByCommunity(idCommunity) {
	const rows = await db.query("SELECT * FROM Vote WHERE idCommunity = " + idCommunity + ";", "");
	return rows;
}





async function getVoteListByCommunityAndroid(idCommunity) {
	const rows = await db.query("SELECT idVote, title, body FROM Vote WHERE idCommunity = " + idCommunity + ";", "");
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




async function createVote(body) {
	const request = "INSERT INTO Vote(idVote, title, body, nbChoices, important, idAdmin, voteBegins, voteEnds, idCommunity) VALUES (null, '" + body.title + "', '" + body.body + "', " + body.nbChoice + ", " + body.important + ", " + body.idAdmin + ",'" + today + "', '" + nextDay + "', " + body.idCommunity + ");";

	const rows = await db.query(request, "");
	return rows;
}




async function deleteVote(idVote) {
	//TODO code

	// TODO remove all votes linked to idVote
	var request = "DELETE FROM Votes WHERE idVote = " + idVote + ";";
	await db.query(request, "");

	//TODO remove all voteOptions linked to idVote
	request = "DELETE FROM VoteOptions WHERE idVote = " + idVote + ";";
	await db.query(request, "");

	//TODO remove idVote
	request = "DELETE FROM Vote WHERE idVote = " + idVote + ";";
	const rows = await db.query(request, "");
	return rows;
}




async function lastPosts(idCommunity) {
	request = "SELECT idVote, title FROM Vote WHERE idCommunity = " + idCommunity + " ORDER BY idVote DESC LIMIT 5;";
	const rows = await db.query(request, "");
	return rows;
}



async function getVoteInfo(idVote) {
	const request = "SELECT * FROM Vote WHERE idVote = " + idVote + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function updatePost(idVote, title, body, nbChoices, voteBegins, voteEnds) {

	/*
		# 			var title = req.body.title;
		# 			var body = req.body.body;
		#			var nbChoice = req.body.nbChoice;
		#			var voteBegins = req.body.voteBegins;
		#			var voteEnds = req.body.voteEnds;
		UPDATE Vote SET  title = ''
	
	*/
	const request = "UPDATE Vote SET title = '" + title + "', body = '" + body + "', nbChoices = " + nbChoices + ", voteBegins = '" + voteBegins + "', voteEnds = '" + voteEnds + "' WHERE idVote = " + idVote + ";";

	const rows = await db.query(request, "");
	return rows;
}

module.exports = {
	getVoteListByCommunity,
	getVoteTitleBody,
	getVoteOptions,
	getNbChoices,
	getListOfNumber,
	getVoteListByCommunityAndroid,
	createVote,
	deleteVote,
	lastPosts,
	getVoteInfo,
	updatePost
}
