
const express = require('express');
const router = express.Router();
const user = require('../services/user.js');

/*router.get('/login', async function (req, res, next) {
	try {
		res.status(200).json(await user.userLogin(req.params.em));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});*/

router.post('/login', async function (req, res, next) {
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
		if (req.body.password != userCredentials[0].password) {
			res.status(403).json({ 'ERROR': "incorrect password" });
			next();
		} else {
			//generate token
			res.status(201).json({
				"idUser": userCredentials[0].idUser
			});
		}
	}
});

router.get('/userInfos/:idUser', async function (req, res, next) {
	try {
		res.status(200).json(await user.getUserInfos(req.params.idUser));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
})

router.put('/update', async function (req, res, next) {
	const oldUser = await user.getUserInfos(req.body.idUser);

	try {
		// récupération de champs de la requete
		var newFirstname = req.body.firstname;
		var newLastname = req.body.lastname;
		var newEmail = req.body.email;

		if (newFirstname == null) {
			newFirstname = oldUser.firstname;
		}

		if (newLastname == null) {
			newLastname = oldUser.lastname;
		}

		if (newEmail == null) {
			newEmail = oldUser.email;
		}

		await user.updateUser(req.body.idUser, newFirstname, newLastname, newEmail)
		res.status(200).json({ "Message": "User updated successfully" });
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

router.put('/update/password', async function (req, res, next) {
	const oldUser = await user.getUserInfosPassword(req.body.idUser);

	try {
		// récupération de champs de la requete
		var newPassword = req.body.password;

		if (newPassword == null) {
			res.status(404).json({ "ERROR": "Missing argument" });
		} else {
			await user.updatePasswordUser(req.body.idUser, newPassword)
			res.status(404).json({ "Message": "Password user updated successfully" });
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

module.exports = router;
