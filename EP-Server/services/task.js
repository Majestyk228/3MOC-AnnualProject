const db = require('./database.js');

async function getTask(idTask) {
    const request = "SELECT * FROM Task WHERE idTask = " + idTask + ";";
    const rows = await db.query(request, "");
    return rows[0];
}

async function insertTask(body) {
    const request = "INSERT INTO Task VALUES (null, \"" + body.title + "\",\"" + body.description + "\"," + body.idUser + "," + body.idList + "," + body.idTag + ");";
    const rows = await db.query(request, "");
    return rows;
}

async function deleteTask(idTask) {
    const request = "DELETE FROM Task WHERE idTask = " + idTask + ";";
    const rows = await db.query(request, "");
    return rows;
}

async function getTasksFromList(idList) {
    const request = "SELECT * FROM Task WHERE idList = " + idList + ";";
    const rows = await db.query(request, "");
    return rows;
}


async function updateTask(idTask, newTitle, newDescription, newIdList, newIdTag) {
    const request = "UPDATE Task SET title = \"" + newTitle + "\",description = \"" + newDescription + "\",idList = " + newIdList + ", idTag = " + newIdTag + " WHERE idTask = " + idTask + "; ";
    console.log("request DB = " + request + "\n\n");
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getTask,
    insertTask,
    deleteTask,
    getTasksFromList,
    updateTask
}
