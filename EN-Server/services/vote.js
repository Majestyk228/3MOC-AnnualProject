const { request } = require('express');
const db = require('./database.js');

//getting current date and time
var today = new Date();


var nextDay = new Date();
nextDay.setDate(nextDay.getDate() + 7);



//var time = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();


var dd7 = String(nextDay.getDate()).padStart(2, '0');
var mm7 = String(nextDay.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy7 = nextDay.getFullYear();

var dd7 = String(nextDay.getDate()).padStart(2, '0');
today = yyyy + '-' + mm + '-' + dd;
nextDay = yyyy7 + '-' + mm7 + '-' + dd7;
//var Datetime = (today + ' ' + time);


async function getVoteListByCommunity(idCommunity) {
	const rows = await db.query("SELECT * FROM Vote WHERE idCommunity = " + idCommunity + " ORDER BY idVote DESC;", "");
	return rows;
}



async function getVoteListByCommunityAndroid(idCommunity) {
	const rows = await db.query("SELECT idVote, title, body FROM Vote WHERE idCommunity = " + idCommunity + " ORDER BY idVote DESC;", "");
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
	const rows = await db.query(query, "");
	return rows;
}

async function getVoteOptionsComplete(idVote, list) {
	for (const element of list) {
		const contents = await getNbChoices(idVote, element.idVoteOptions);
		element.nbChoice = contents[0].nbChoice;
	}

	return list
}


async function getListOfNumber(idVote, choice) {

	var number = 0;

	number = await getNbChoices(idVote, choice.idVoteOptions)

	return number[0].nbChoice;
}




async function createVote(body) {
	const request = "INSERT INTO Vote(idVote, title, body, nbChoices, important, idAdmin, voteBegins, voteEnds, idCommunity) VALUES (null, \"" + body.title + "\", \"" + body.body + "\", " + body.nbChoice + ", " + body.important + ", " + body.idAdmin + ",'" + today + "', '" + nextDay + "', " + body.idCommunity + ");";

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


async function getVoteOptionsAndroid(idVote) {
	const request = "SELECT * FROM VoteOptions WHERE idVote = " + idVote + ";";

	const rows = await db.query(request, "");
	return rows;
}



async function insertVoteOptions(body) {
	var request = "INSERT INTO VoteOptions VALUES ";


	// BUILDING REQUEST
	body.forEach(voteOption => {
		request += "(null, \"" + voteOption.label + "\", " + voteOption.idVote + "),";
	});


	// REMOVING LAST , UNNEEDED
	request = request.slice(0, -1);

	request += ";";

	const rows = await db.query(request, "");
	return rows;
}

async function getLastVote() {
	const request = "SELECT idVote FROM Vote ORDER BY idVote DESC LIMIT 1;";
	const rows = await db.query(request, "");
	return rows;
}


async function insertVoteUser(body) {
	const request = "INSERT INTO Votes (idUser, idVote, choice) VALUES (" + body.idUser + "," + body.idVote + "," + body.choice + ");";
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
	updatePost,
	getVoteOptionsAndroid,
	insertVoteOptions,
	getLastVote,
	insertVoteUser,
	getVoteOptionsComplete
}
