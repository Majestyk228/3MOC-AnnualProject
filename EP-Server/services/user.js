const db = require('./database.js');

async function userLogin(email) {
	const request = "SELECT idUser, password FROM User WHERE email = \"" + email + "\";";
	const rows = await db.query(request, "");
	return rows;
}


async function getUserCredentials(email) {
	const request = "SELECT idUser, password FROM User WHERE email = \"" + email + "\";";
	const rows = await db.query(request, "");
	return rows;
}

async function getUserInfos(idUser) {
	const request = "SELECT firstname, lastname, email FROM User WHERE idUser = " + idUser + ";";
	const rows = await db.query(request, "");
	return rows[0];
}


module.exports = {
	getUserCredentials,
	userLogin,
	getUserInfos
}
