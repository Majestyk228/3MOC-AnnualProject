const db = require('./database.js');

async function getAllTag() {
    const request = "SELECT * FROM Tag;";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getAllTag
}