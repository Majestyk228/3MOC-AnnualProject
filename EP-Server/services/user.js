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

async function getUserInfosPassword(idUser) {
	const request = "SELECT password FROM User WHERE idUser = " + idUser + ";";
	const rows = await db.query(request, "");
	return rows[0];
}

async function updateUser(idUser, newFirstname, newLastname, newEmail) {
	const request = "UPDATE User SET firstname = \"" + newFirstname + "\",lastname = \"" + newLastname + "\",email = \"" + newEmail + "\" WHERE idUser = " + idUser + "; ";
	const rows = await db.query(request, "");
	return rows[0];
}

async function updatePasswordUser(idUser, newPassword) {
	const request = "UPDATE User SET password = \"" + newPassword + "\" WHERE idUser = " + idUser + "; ";
	const rows = await db.query(request, "");
	return rows[0];
}


module.exports = {
	getUserCredentials,
	userLogin,
	getUserInfos,
	updateUser,
	getUserInfosPassword,
	updatePasswordUser
}
