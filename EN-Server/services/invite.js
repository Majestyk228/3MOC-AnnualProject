const db = require('./database.js');

async function insertInvite() {

	// TODO : test route after dealing with Community
	const result = await db.query("");

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

module.exports = {
	insertInvite,
	getAllInvites
}
