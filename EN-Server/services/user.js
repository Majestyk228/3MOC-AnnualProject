const db = require('./database.js');
////const helper = require('../helper');
////const config = require('../config/config.js');
const bcryptUtils = require('../utils/bcrypt.utils.js');

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

async function insertUser(userReq) {
    //const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + bcryptUtils.hashPwd(userReq.password) + "', 0);";
    const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + userReq.password + "', 0);";
    console.log("\n\n" + requete);
    const results = await db.query(requete, "");

    //message to output at the end of the function
    let message = 'Error in creating contact';

    if (results.affectedRows) {
        message = 'Contact created successfully';
    }

    return { message };
}

async function findUser(email) {
    const rows = await db.query("SELECT idUser FROM User WHERE email='" + email + "';", "");
    return rows[0];
}

async function getAllReportedUsers() {
    const rows = await db.query("SELECT u.idUser, c.reports FROM User u, Comment c WHERE c.idUser = u.idUser and c.reports>0;", "");
    return rows;
}

async function getAllPointOrderedUsers() {
    const rows = await db.query("SELECT idUser, firstName, lastName, birthDate, gender, areaCode, email, points FROM User ORDER BY points DESC;", "");
    return rows;
}

module.exports = {
    getAllUsers,
    getUserInfo,
    getUserCredentials,
    insertUser,
    findUser,
    getAllReportedUsers,
    getAllPointOrderedUsers
}
