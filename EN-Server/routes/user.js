const express = require('express');
const router = express.Router();
const user = require('../services/user.js');
const jwtUtils = require('../utils/jwt.utils.js');
const config = require('../config/config.js');
var jwt = require('jsonwebtoken');
var bcrypt = require('bcryptjs');






router.get('/nbPosts/:idUser', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getNbPostsFromUser(req.params.idUser));
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




router.get('/nbComments/:idUser', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getNbCommentsFromUser(req.params.idUser));
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





/* GET allUsers ordered by points*/
router.get('/all/points/:idCommunity', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getAllPointOrderedUsers(req.params.idCommunity));
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




/* GET allUsers that has at least 1 comment reported*/
router.get('/all/reports', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getAllReportedUsers());
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




/* GET allUsers*/
router.get('/all/:idCommunity', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getAllUsers(req.params.idCommunity));
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





/* GET userInfo // idUser must be in body request*/
router.post('/infos', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getUserInfo(req.body.idUser));
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






/* POST login // idUser must be in body request*/
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






//POST add user in database
router.post('/register', async function (req, res) {
	// Verify data given
	if (req.body.firstName == null || req.body.lastName == null || req.body.birthDate == null || req.body.gender == null || req.body.areaCode == null || req.body.email == null || req.body.password == null) {
		res.status(400).json([{ 'Error': "missing info(s)" }]);
	}

	//check if user isnt already registered
	if (!await user.findUser(req.body.email)) {
		//insert in db
		const idUser = await user.insertUser1(req.body);
		//const idUser = await user.getLastUserRegistered()[0]
		console.log(idUser)
		res.status(201).json(
			{
				"message": "User successufully registered",
				"idUser": idUser.idUser[0].idUser
			});
	} else {
		res.status(409).json([{ 'Error': "user already has an account" }]);
	}

});






//PUT updateInfo idUser must be passed in request body
router.put('/infos/update', async function (req, res, next) {


	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				if (!req.body.firstName || !req.body.lastName || !req.body.birthDate || !req.body.gender || !req.body.areaCode || !req.body.email) {
					res.status(422).json([{ "ERROR": "Missing argument(s)" }]);
				} else {
					//
					var firstName = req.body.firstName;
					var lastName = req.body.lastName;
					var birthDate = req.body.birthDate;
					var gender = req.body.gender;
					var areaCode = req.body.areaCode;
					var email = req.body.email;

					if (!req.body.idUser) {
						res.status(400).json([{ 'Error': "idUser not in request" }]);
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

					await user.updateUser(req.body.idUser, firstName, lastName, birthDate, gender, areaCode, email);
					res.status(200).json({ "Message": "User Updated" });
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







/* PUT allUsers ordered by points*/
router.put('/password/reset', async function (req, res, next) {


	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				if (req.body.password == null || req.body.idUser == null) {
					res.status(422).json([{ "ERROR": "Missing argument(s)" }]);
				} else {
					await user.updatePasswordUser(req.body.password, req.body.idUser);
					res.status(200).json([{ "Message": "Password updated successfully" }]);
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






/* PUT allUsers ordered by points*/
router.delete('/delete/:idUser', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await user.deleteUser(req.params.idUser);
				res.status(200).json([{ "Message": "User deleted successfully" }]);
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






/* GET 5 last registered users */
router.get('/lastRegistered/:idCommunity', async function (req, res, next) {


	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await user.getLastRegisteredUsers(req.params.idCommunity));
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



/* GET allUsers that has at least 1 comment reported*/
router.post('/register/admin', async function (req, res, next) {


	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				const idUser = await user.insertUser(req.body);
				//insert user in community
				user.addUserToCommunity(idUser.idUser[0].idUser, req.body.idCommunity);

				res.status(400).json([{ "message": "User created successfully." }]);
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

router.post('/addToCommunity', async function (req, res, next) {


	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				user.addUserToCommunity(req.body.idUser, req.body.idCommunity);

				res.status(400).json([{ "message": "User added to community successfully." }]);
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
