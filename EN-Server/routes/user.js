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

module.exports = router;
