//IMPORTATION
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







async function insertInvite(bodyReq) {

	const result = await db.query("INSERT INTO Invitation(code, idCommunity, creationDate, endDate) VALUES (null, " + bodyReq.idCommunity + ",'" + today + "','" + nextDay + "');");
	//INSERT INTO Invitation(code, idCommunity, creationDate, endDate) VALUES (null, 1, STR_TO_DATE('07-05-2022', '%d-%m-%Y'),STR_TO_DATE('17-05-2022', '%d-%m-%Y'));

	let message = 'Error in creating Invitation';

	if (result.affectedRows) {
		message = 'Invitation created successfully';
	}

	return { message };
}

async function getAllInvites() {
	const rows = await db.query("SELECT * FROM Invitation;", "");
	return rows;
}

async function getLastInvite(idCommunity) {
	const rows = await db.query("SELECT code FROM Invitation WHERE idCommunity = " + idCommunity + " ORDER BY code DESC LIMIT 1;", "");
	return rows;
}


async function getAllCommunityInvites(idCommunity) {
	const request = "SELECT * FROM Invitation WHERE idCommunity = " + idCommunity + ";";
	const rows = await db.query(request, "");
	return rows;
}



async function deleteInvite(code) {
	const request = "DELETE FROM Invitation WHERE code = " + code + ";";
	const rows = await db.query(request, "");
	return rows;
}

async function getCommunity(code) {
	const request = "SELECT idCommunity FROM Invitation WHERE code = " + code + ";";
	const rows = await db.query(request, "");
	return rows[0];
}

module.exports = {
	insertInvite,
	getAllInvites,
	getLastInvite,
	getAllCommunityInvites,
	deleteInvite,
	getCommunity
}
