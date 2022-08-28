const express = require('express');
const router = express.Router();
const admin = require('../services/admin.js');
const jwtUtils = require('../utils/jwt.utils.js');
var bcrypt = require('bcryptjs');
const config = require('../config/config.js');
var jwt = require('jsonwebtoken');




// email must be in body request
router.post('/loginSecure', async function (req, res, next) {
	//verifying credentials entered
	if (req.body.email == null || req.body.password == null) {
		res.status(400).json([{ 'ERROR': 'Missing email or/and password' }]);
		next();
	}

	//Searching for user with email entered
	const adminCredentials = await admin.getAdminCredentials(req.body.email);
	if (JSON.stringify(adminCredentials) == "[]") {
		res.status(404).json({ 'ERROR': "cannot find admin" });
		next();
	} else {
		if (bcrypt.compareSync(req.body.password, adminCredentials[0].password) == false) {
			res.status(403).json({ 'ERROR': "incorrect password" });
			next();
		} else {
			res.status(201).json({
				"idAdmin": adminCredentials[0].idAdmin,
				"idCommunity": adminCredentials[0].idCommunity,
				"token": jwtUtils.generateTokenForAdmin(adminCredentials[0])
			});
		}
	}
});




router.put('/password/reset', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				if (req.body.password == null || req.body.idAdmin == null) {
					res.status(422).json([{ "ERROR": "Missing argument(s)" }]);
				} else {
					await admin.updatePasswordAdmin(req.body.password, req.body.idAdmin);
					res.status(200).json([{ "Message": "Admin password updated successfully" }]);
				}
			} catch (err) {
				// IF TOKEN IS INVALID
				res.status(406).json([{ "ERROR": "Token expired/incorrect" }]);
			}
		} else {
			res.status(404).json([{ "ERROR": "Missing token in header" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

module.exports = router;


//updateAdminPassword.js
