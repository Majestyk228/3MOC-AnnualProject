const mysql = require('mysql2/promise');
const config = require('../config/config.js');

async function query(sql) {
	const connection = await mysql.createConnection(config.config.db);
	const [results,] = await connection.execute(sql);

	return results;
}

module.exports = {
	query
}
