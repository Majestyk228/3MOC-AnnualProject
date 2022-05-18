const db = require('./database.js');

async function getAllAdmin() {
	const rows = await db.query("SELECT * FROM Admin;", "");
	return rows;
}


//get idAdmin and Password by email
async function getAdminCredentials(email) {
	/* ======= password will be crypted ======= */

	const rows = await db.query("SELECT idAdmin, password FROM Admin WHERE email='" + email + "';", "");
	return rows;
}

module.exports = {
	getAllAdmin,
	getAdminCredentials
}
