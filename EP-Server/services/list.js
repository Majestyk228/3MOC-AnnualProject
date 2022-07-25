const db = require('./database.js');

async function getListOfUser(idUser) {
    const request = "SELECT * FROM List WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}

async function insertList(body) {
    const request = "INSERT INTO List VALUES (null, \"" + body.title + "\", " + body.idUser + ");";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getListOfUser,
    insertList
}
