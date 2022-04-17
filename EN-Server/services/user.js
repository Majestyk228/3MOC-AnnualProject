const db = require('./database.js');
//const helper = require('../helper');
const config = require('../config/config.js');
const bcrypt = require('bcrypt');

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
    bcrypt.hash(userRed.password, 5, function (err, hashedPwd) {
        const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "',STR_TO_DATE('" + userReq.birthDate + "', '%d-%m-%Y'),'" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + hashedPwd + "', 0);";

        const results = await db.query(requete, "");

        //message to output at the end of the function
        let message = 'Error in creating contact';

        if (results.affectedRows) {
            message = 'Contact created successfully';
        }

        return { message };
    });

}



module.exports = {
    getAllUsers,
    getUserInfo,
    getUserCredentials,
    insertUser
}
