const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const vote = require('../services/vote.js');
const pm = require('../services/pointsManager.js');
const config = require('../config/config.js');
var jwt = require('jsonwebtoken');

//GET CURRENT TIME
var today = new Date();
var time = today.getHours() + ':' + today.getMinutes();

// FIREBASE CONFIGURATION
var admin = require("firebase-admin");
const serviceAccount = require("../config/exprimons-nous-firebase-adminsdk-tinzt-bba1677dec.json");
//var fcm = new fcm(serviceAccount)
admin.initializeApp({
	credential: admin.credential.cert(serviceAccount)
});



/* GET VoteListByCommunity idCommunity MUST BE IN BODY*/
router.get('/voteList/:idCommunity', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await vote.getVoteListByCommunity(req.params.idCommunity));
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



/* GET Vote's infos, their choices and the number of time each choices has been selected MUST BE IN BODY*/
router.post('/voteInfo', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				//TODO : take title and body
				const titleBody = await vote.getVoteTitleBody(req.body.idVote)

				//TODO : Take choices list
				const choiceList = await vote.getVoteOptions(req.body.idVote)

				const choiceListComplet = await vote.getVoteOptionsComplete(req.body.idVote, choiceList);

				//TODO : Take number for selection for each choice

				var nbSelected = []

				choiceList.forEach(choice => {
					nbSelected.push(vote.getListOfNumber(req.body.idVote, choice)).value
				})

				console.log("nbselected = " + nbSelected)

				res.status(200).json(
					[
						{
							"title": titleBody[0].title,
							"body": titleBody[0].body,
							"voteOptions": choiceListComplet,
						}
					]
				);
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



/* GET VoteListByCommunityAndroid idCommunity MUST BE IN BODY*/
router.post('/voteListAndroid', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await vote.getVoteListByCommunityAndroid(req.body.idCommunity));
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







/*// TODO Test route
server.post('/firebase/notification', (req, res) => {
	const registrationToken = req.body.registrationToken
	const message = req.body.message
	const options = notification_options

	admin.messaging().sendToDevice(registrationToken, message, options)
		.then(response => {

			res.status(200).send("Notification sent successfully")

		})
		.catch(error => {
			console.log(error);
		});

})*/





// CREATE a vote
router.post('/create', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await vote.createVote(req.body);
				const idVote = await vote.getLastVote();

				// NOTIFICATION VIA FCM
				const topic = 'idCommunity' + req.body.idCommunity;

				const message = {
					data: {
						score: '850',
						time: time
					},
					topic: topic
				};





				const messaging = admin.messaging()
				var payload = {
					notification: {
						title: "Un nouveau vote a été posté !",
						body: "This is the body of the notification message."
					},
					topic: topic
				};


				messaging.send(payload)
					.then((result) => {
						console.log(result)
					})
					.catch((error) => {
						console.log('Error sending message:', error);
					});



				//SEND MESSAGE TO SUBSCRIBED DEVICES 
				/*admin.getMessaging().send(message)
					.then((response) => {
						// Response is a message ID string.
						console.log('Successfully sent message:', response);
					})
					.catch((error) => {
						console.log('Error sending message:', error);
					});*/


				res.status(200).json({
					"Message": "Vote created successfully.",
					"idVote": idVote[0].idVote
				});

			} catch (err) {
				// IF TOKEN IS INVALID
				//res.status(406).json([{ "ERROR": "Token expired/incorrect" }]);
				res.status(406).json([{ "ERROR": err.message }]);
			}
		} else {
			res.status(404).json([{ "ERROR": "Missing token in header" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});


// DELETE a vote
router.delete('/delete/:idVote', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await vote.deleteVote(req.params.idVote);
				res.status(200).json({ "Message": "Vote deleted successfully." });
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



router.get('/lastVotes/:idCommunity', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await vote.lastPosts(req.params.idCommunity));
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


// UPDATE A vote
router.put('/updateVote', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				// if missing idVote
				if (!req.body.idVote) {
					res.status(422).json([{ "ERROR": "Missing argument(s)" }]);
				} else {
					var title = req.body.title;
					var body = req.body.body;
					var nbChoices = req.body.nbChoices;
					var voteBegins = req.body.voteBegins;
					var voteEnds = req.body.voteEnds;

					const voteDB = await vote.getVoteInfo(req.body.idVote);


					if (title == null) {
						title = voteDB[0].title;
					}

					if (body == null) {
						body = voteDB[0].body;
					}

					if (nbChoices == null || nbChoices <= 1) {
						nbChoices = voteDB[0].nbChoices;
					}

					if (voteBegins == null) {
						voteBegins = voteDB[0].voteBegins;
					}

					if (voteEnds == null) {
						voteEnds = voteDB[0].voteEnds;
					}


					await vote.updatePost(req.body.idVote, title, body, nbChoices, voteBegins, voteEnds);
					res.status(201).json([{ "Message": "Vote updated successfully" }]);

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




router.post('/options', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await vote.getVoteOptionsAndroid(req.body.idVote));
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





router.post('/infos', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await vote.getVoteInfo(req.body.idVote));
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





router.post('/newVoteOptions', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await vote.insertVoteOptions(req.body);
				res.status(200).json([{ "Message": "Vote Options created successfully" }]);
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



router.post('/userVote', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await vote.insertVoteUser(req.body);
				res.status(200).json([{ "Message": "Vote User submitted successfully" }]);
			} catch (err) {
				// IF TOKEN IS INVALID
				//es.status(406).json([{ "ERROR": "Token expired/incorrect" }]);
				res.status(404).json([{ "ERROR": err.message }]);
			}

			await pm.addPoints(req.body.idUser, 15);
		} else {
			res.status(404).json([{ "ERROR": "Missing token in header" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});



module.exports = router;
