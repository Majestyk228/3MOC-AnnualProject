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
    const rows = await db.query("SELECT idUser, firstName, lastName, birthDate, gender, areaCode, email, points FROM User WHERE idUser='" + idUser + "';", "");
    return rows;
}

//get idUser and Password by email
async function getUserCredentials(email) {
    /* ======= password will be crypted ======= */

    const rows = await db.query("SELECT u.idUser, u.password, a.idCommunity FROM User u, Associate a WHERE a.idUser = u.idUser AND email='" + email + "';", "");
    return rows;
}

async function insertUser(userReq) {
    //const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + bcryptUtils.hashPwd(userReq.password) + "', 0);";
    const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + userReq.password + "', 0);";
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

async function updateUser(idUser, firstName, lastName, birthDate, gender, areaCode, email) {
    const request = "UPDATE User SET firstName = '" + firstName + "', lastName = '" + lastName + "', birthDate = '" + birthDate + "', gender = '" + gender + "', areaCode = '" + areaCode + "', email = '" + email + "' WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}

async function updatePasswordUser(password, idUser) {
    const request = "UPDATE User SET password = '" + password + "' WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}


async function deleteUser(idUser) {
    const request = "DELETE FROM User WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}




async function getLastRegisteredUsers(idCommunity) {
    const request = "SELECT u.idUser, firstName, lastName FROM User u, Associate a WHERE a.idCommunity = " + idCommunity + " AND u.idUser = a.idUser ORDER BY u.idUser DESC LIMIT 5;";
    const rows = await db.query(request, "");
    return rows;
}

module.exports = {
    getAllUsers,
    getUserInfo,
    getUserCredentials,
    insertUser,
    findUser,
    getAllReportedUsers,
    getAllPointOrderedUsers,
    updateUser,
    updatePasswordUser,
    deleteUser,
    getLastRegisteredUsers
}
