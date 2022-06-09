const db = require('./database.js');

async function getAllAdmin() {
	const rows = await db.query("SELECT * FROM Admin;", "");
	return rows;
}


//get idAdmin and Password by email
async function getAdminCredentials(email) {
	/* ======= password will be crypted ======= */

	//SELECT a.idAdmin, a.email, a.password, c.idCommunity, c.label FROM Admin a, Community c WHERE a.idCommunity = c.idCommunity and a.email = "test@test.com";

	//const rows = await db.query("SELECT idAdmin, email ,password FROM Admin WHERE email='" + email + "';", "");
	const rows = await db.query("SELECT a.idAdmin, a.email, a.password, c.idCommunity FROM Admin a, Community c WHERE a.idCommunity = c.idCommunity and a.email ='" + email + "';", "");
	return rows;
}

module.exports = {
	getAllAdmin,
	getAdminCredentials
}
