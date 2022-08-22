const db = require('./database.js');



async function addPoints(idUser, points) {
	console.log("in pm")
	const request = "UPDATE User SET points = points + " + points + " WHERE idUser = " + idUser + ";";
	const rows = await db.query(request, "");
	//return rows;
}


async function removePoints(idUser, points) {
	const request = "UPDATE User SET points = points-" + points + " WHERE idUser = " + idUser + ";";
	console.log(request)
	const rows = await db.query(request, "");
	//return rows;
}


module.exports = {
	addPoints,
	removePoints
}
