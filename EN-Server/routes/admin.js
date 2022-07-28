const express = require('express');
const router = express.Router();
const admin = require('../services/admin.js');






router.get('/all', async function (_, res, next) {

	try {
		res.status(200).json(await admin.getAllAdmin());
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});








// email must be in body request
router.post('/login', async function (req, res, next) {
	try {
		res.status(200).json(await admin.getAdminCredentials(req.body.email));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});


// email must be in body request
router.post('/loginSecure', async function (req, res, next) {
	//verifying credentials entered
	if (req.body.email == null || req.body.password == null) {
		res.status(400).json([{ 'ERROR': 'Missing email or/and password' }]);
		next();
	}

	//Searching for user with email entered
	const userCredentials = await user.getUserCredentials(req.body.email);
	if (JSON.stringify(userCredentials) == "[]") {
		res.status(404).json({ 'ERROR': "cannot find user" });
		next();
	} else {
		//if (req.body.password != userCredentials[0].password) {

		//console.log("DB => " + userCredentials[0].password)
		console.log("compareSync => " + bcrypt.compareSync(req.body.password, userCredentials[0].password))

		if (bcrypt.compareSync(req.body.password, userCredentials[0].password) == false) {
			res.status(403).json({ 'ERROR': "incorrect password" });
			next();
		} else {
			//generate token
			res.status(201).json({
				"idUser": userCredentials[0].idUser,
				"idCommunity": userCredentials[0].idCommunity,
				"token": jwtUtils.generateTokenForUser(userCredentials[0].idUser)
			});
		}
	}
});

module.exports = router;
