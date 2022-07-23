const db = require('./database.js');

async function getListOfUser(idUser) {
    const request = "SELECT * FROM List WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getListOfUser
}