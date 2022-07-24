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


module.exports = {
	getUserCredentials,
	userLogin
}
