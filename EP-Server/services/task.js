const db = require('./database.js');

async function getTask(idTask) {
    const request = "SELECT * FROM Task WHERE idTask = " + idTask + ";";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getTask
}