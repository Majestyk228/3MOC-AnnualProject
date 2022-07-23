const db = require('./database.js');

async function getAllCommentsFromTask(idTask) {
    const request = "SELECT u.firstname, u.lastname, c.body, c.date FROM Comment c, User u WHERE u.idUser = c.idUser AND idTask = " + idTask + ";";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getAllCommentsFromTask
}