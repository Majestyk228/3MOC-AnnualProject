const express = require('express');
const router = express.Router();
const admin = require('../services/admin.js');
//const jwtUtils = require('../utils/jwt.utils.js');






router.get('/all', async function (_, res, next) {

	try {
		res.status(200).json(await admin.getAllAdmin());
	} catch (err) {
		//console.error(`Error while getting community `, err.message);
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});








// email must be in body request
router.post('/login', async function (req, res, next) {

	//verifying credentials entered
	/*if (req.body.email == null || req.body.password == null) {
		res.status(400).json([{ 'Error': 'Missing email or/and password' }]);
		next();
	}

	//Searching for user with email entered
	const adminCredentials = await admin.getAdminCredentials(req.body.email);

	if (JSON.stringify(adminCredentials) == "[]") {
		//error
		res.status(404).json([{ 'Error': "cannot find admin" }]);
		next();
	} else {
		if (req.body.password != adminCredentials[0].password) {
			//error
			res.status(403).json([{ 'Error': "incorrect password" }]);
			next();
		} else {
			//generate token
			res.status(200).json([{
				'idAdmin': adminCredentials[0].idAdmin,
				//'token': jwtUtils.generateTokenForUser(userCredentials[0].idUser)
			}]);
		}
	}*/

	try {
		res.status(200).json(await admin.getAdminCredentials(req.body.email));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});

module.exports = router;
