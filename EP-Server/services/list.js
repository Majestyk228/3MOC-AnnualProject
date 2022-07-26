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

async function deleteList(idList) {
    // DELETING ALL TASK IN THE LIST
    var request = "DELETE FROM Task WHERE idList = " + idList + ";";
    var rows = await db.query(request, "");

    // DELETING THE EMPTY LIST
    request = "DELETE FROM List WHERE idList = " + idList + ";";
    rows = await db.query(request, "");
    return rows;
}

async function getList(idList) {
    const request = "SELECT * FROM List WHERE idList = " + idList + ";";
    const rows = await db.query(request, "");
    return rows[0];
}

async function updateList(idList, title) {
    const request = "UPDATE List SET title = \"" + title + "\" WHERE idList = " + idList + ";";
    const rows = await db.query(request, "");
    return rows[0];
}

module.exports = {
    getListOfUser,
    insertList,
    deleteList,
    getList,
    updateList
}
