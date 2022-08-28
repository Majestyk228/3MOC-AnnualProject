// ===== IMPORTS =====
const db = require('./database.js');
var bcrypt = require('bcryptjs');


// ===== FUNCTIONS ======




//gives the list of All users on the platform
async function getAllUsers(idCommunity) {
    const rows = await db.query("SELECT u.idUser, firstName, lastName, birthDate, gender, areaCode, email, points FROM User u, Associate a WHERE a.idCommunity = " + idCommunity + " ANd u.idUser = a.idUser;", "");
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
    const request = "SELECT u.idUser, u.password, a.idCommunity FROM User u, Associate a WHERE a.idUser = u.idUser AND email=\"" + email + "\";";
    const rows = await db.query(request, "");
    return rows;
}


async function insertUser(userReq) { // WILL BE DELETED
    //const requete = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + bcryptUtils.hashPwd(userReq.password) + "', 0);";


    var request = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points, signInDate) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + userReq.password + "', 0, CURDATE());";
    var results = await db.query(request, "");


    //message to output at the end of the function
    let message = 'Error in creating User';
    let idUser;

    if (results.affectedRows) {
        message = 'User created successfully';
        idUser = await getLastUserRegistered()
    }

    bcrypt.genSalt(10, async function (err, salt) {
        bcrypt.hash(userReq, salt, async function (err, hash) {
            // Store hash in your password DB.
            const request = "UPDATE User SET password = \"" + hash + "\" WHERE idUser = " + idUser.idUser + ";";
            const results = await db.query(request, "");
        });
    });

    return { idUser };
}


async function insertUser1(userReq) {

    var hash = bcrypt.hashSync(userReq.password, 10);
    var request = "INSERT INTO User (idUser, firstName, lastName, birthDate, gender, areaCode, email, password, points, signInDate) VALUES (null,'" + userReq.firstName + "','" + userReq.lastName + "','" + userReq.birthDate + "','" + userReq.gender + "', '" + userReq.areaCode + "','" + userReq.email + "','" + hash + "', 0, CURDATE());";
    var results = await db.query(request, "");

    let idUser;


    message = 'User created successfully';
    idUser = await getLastUserRegistered()

    return { idUser };
}


async function findUser(email) {
    const rows = await db.query("SELECT idUser FROM User WHERE email='" + email + "';", "");
    return rows[0];
}


async function getAllReportedUsers() {
    const rows = await db.query("SELECT u.idUser, c.reports FROM User u, Comment c WHERE c.idUser = u.idUser and c.reports>0;", "");
    return rows;
}



async function getAllPointOrderedUsers(idCommunity) {
    const rows = await db.query("SELECT u.idUser, u.firstName, u.lastName, u.birthDate, u.gender, u.areaCode, u.email, u.points FROM User u, Associate a WHERE a.idUser = u.idUser AND a.idCommunity = " + idCommunity + " ORDER BY points DESC;", "");
    return rows;
}


async function updateUser(idUser, firstName, lastName, birthDate, gender, areaCode, email) {
    const request = "UPDATE User SET firstName = '" + firstName + "', lastName = '" + lastName + "', birthDate = '" + birthDate + "', gender = '" + gender + "', areaCode = '" + areaCode + "', email = '" + email + "' WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}


async function updatePasswordUser(password, idUser) {
    var hash = bcrypt.hashSync(password, 10);
    const request = "UPDATE User SET password = '" + hash + "' WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}


async function deleteUser(idUser) {
    var request = "DELETE FROM Associate WHERE idUser = " + idUser + ";";
    var rows = await db.query(request, "");

    request = "DELETE FROM User WHERE idUser = " + idUser + ";";
    rows = await db.query(request, "");
    return rows;
}


async function getLastRegisteredUsers(idCommunity) {
    const request = "SELECT u.idUser, firstName, lastName FROM User u, Associate a WHERE a.idCommunity = " + idCommunity + " AND u.idUser = a.idUser ORDER BY u.idUser DESC LIMIT 5;";
    const rows = await db.query(request, "");
    return rows;
}


async function getLastUserRegistered() {
    const request = "SELECT idUser FROM User ORDER BY idUser DESC LIMIT 1;";
    const rows = await db.query(request, "");
    return rows;
}


async function addUserToCommunity(idUser, idCommunity) {
    const request = "INSERT INTO Associate VALUES(" + idUser + ", " + idCommunity + "); ";
    const rows = await db.query(request, "");
    return rows[0];
}

//getNbPostsFromUser
async function getNbPostsFromUser(idUser) {
    const request = "SELECT COUNT(*) as nbPosts FROM Post WHERE idUser = " + idUser + ";";
    const rows = await db.query(request, "");
    return rows;
}



//getNbPostsFromUser
async function getNbCommentsFromUser(idUser) {
    const request = "SELECT COUNT(*) as nbComments FROM Comment WHERE idUser = " + idUser + ";";
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
    getLastRegisteredUsers,
    getLastUserRegistered,
    addUserToCommunity,
    insertUser1,
    getNbPostsFromUser,
    getNbCommentsFromUser
}
