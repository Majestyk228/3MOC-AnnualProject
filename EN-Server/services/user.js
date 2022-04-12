const db = require('./database.js');
//const helper = require('../helper');
const config = require('../config/config.js');

//gives the list of All users on the platform
async function getAllUsers() {
    /*query below for debugging purposes DO NOT USE IN PROD
    *
    *const rows = await db.query("SELECT * FROM User;", "");
    */

    const rows = await db.query("SELECT idUser, firstName, lastName, birthDate, gender, areaCode, email, points FROM User;", "");
    return rows;
}

//gives every information on ONE specific user passed in parameter
async function getUserInfo(idUser) {
    /*query below for debugging purposes DO NOT USE IN PROD
    *
    /const rows = await db.query("SELECT * FROM User WHERE idUser='" + idUser + "';", "");
    */

    const rows = await db.query("SELECT idUser, firstName, lastName, birthDate, gender, areaCode, email, points FROM User WHERE idUser='" + idUser + "';", "");
    return rows;
}

//get idUser and Password by email
async function getUserCredentials(email) {
    /* ======= password will be crypted ======= */

    const rows = await db.query("SELECT idUser, password FROM User WHERE email='" + email + "';", "");
    return rows;
}



module.exports = {
    getAllUsers,
    getUserInfo,
    getUserCredentials
}
