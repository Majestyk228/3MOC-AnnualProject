const express = require('express');
const router = express.Router();
const user = require('../services/user.js');
const jwtUtils = require('../utils/jwt.utils.js');


/* GET allUsers*/
router.get('/all', async function (_, res, next) {
	try {
		res.json(await user.getAllUsers());
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});

/* GET userInfo // idUser must be in body request*/
router.get('/infos', async function (req, res, next) {
	//comparing passwords

	//generating jwt token
	try {
		res.json(await user.getUserInfo(req.body.idUser));
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});

/* GET userInfo // idUser must be in body request*/
router.get('/login', async function (req, res, next) {
	//verifying credentials entered
	if (req.body.email == null || req.body.password == null) {
		res.status(400).json({ 'Error': 'Missing email or/and password' });
		next();
	}

	//Searching for user with email entered
	const userCredentials = await user.getUserCredentials(req.body.email);
	if (JSON.stringify(userCredentials) == "[]") {
		//error
		res.status(500).json({ 'Error': "cannot find user" });
		next();
	} else {
		if (req.body.password != userCredentials[0].password) {
			//error
			res.status(403).json({ 'Error': "incorrect password" });
			next();
		} else {
			//generate token
			res.status(201).json({
				'idUser': userCredentials[0].idUser,
				'token': jwtUtils.generateTokenForUser(userCredentials[0].idUser)
			});
		}
	}
});

//POST add user in database
router.post('/register', async function (req, res) {
	// TODO Verify data given
	if (req.body.firstName == null || req.body.lastName == null || req.body.birthDate == null || req.body.gender == null || req.body.areaCode == null || req.body.email == null || req.body.password == null) {
		//res 400 -> missing infos
		res.status(400).json({ 'Error': "missing info(s)" });
	} else {
		//check if user isnt already registered
		if (await user.findUser(req.body.email).idUser) {
			//res 409 -> user already registered
			res.status(409).json({ 'Error': "user already has an account" });
		} else {
			//insert in db
			res.status(409).send(user.insertUser(req.body));
		}
	}
});

//PUT updateInfo idUser must be passed in request body
router.put('/infos/update', async function (req, res) {
	// TODO check data passed in request body
	//if a data is missing, we just put back the value already in db
	//else, we put the data passed request

	var firstName = req.body.firstName;
	var lastName = req.body.lastName;
	var birthDate = req.body.birthDate;
	var gender = req.body.Gender;
	var areaCode = req.body.areaCode;
	var email = req.body.email;

	if (!req.body.idUser) {
		res.status(400).json({ 'Error': "idUser not in request" });
	}

	var infoUser = user.getUserInfo(req.body.idUser);

	//verifying if each parameter is empty or not

	if (firstName == "") {
		firstName = infoUser[0].firstName;
	}
	if (lastName == "") {
		lastName = infoUser[0].lastName;
	}
	if (birthDate == "") {
		birthDate = infoUser[0].birthDate;
	}
	if (gender == "") {
		gender = infoUser[0].gender;
	}
	if (areaCode == "") {
		areaCode = infoUser[0].areaCode;
	}
	if (email == "") {
		email = infoUser[0].email;
	}

	// TODO call function updateUser to pass all the variables after checkup

});


/* GET allUsers that has at least 1 comment reported*/
router.get('/all/reports', async function (_, res, next) {
	try {
		res.json(await user.getAllReportedUsers());
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});


/* GET allUsers ordered by points*/
router.get('/all/points', async function (_, res, next) {
	try {
		res.json(await user.getAllPointOrderedUsers());
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});

module.exports = router;
