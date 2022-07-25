const db = require('./database.js');

async function getTask(idTask) {
    const request = "SELECT * FROM Task WHERE idTask = " + idTask + ";";
    const rows = await db.query(request, "");
    return rows;
}

async function insertTask(body) {
    const request = "INSERT INTO Task VALUES (null, \"" + body.title + "\",\"" + body.description + "\"," + body.idUser + "," + body.idList + "," + body.idTag + ");";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getTask,
    insertTask
}
