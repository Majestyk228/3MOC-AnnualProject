const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const community = require('../services/community.js');
const config = require('../config/config.js');
var jwt = require('jsonwebtoken');


router.post('/bestUsers', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				const result = await community.getThreeBestUserByCommunity(req.body.idCommunity)
				if (result.toString == "[]") {
					res.status(404).json([{ "ERROR": "Bad Request" }]);
				} else {
					res.status(200).json(result);
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

//TODO : Corriger les données
router.post('/stats2', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				const result = await community.getCommunityStats(req.body.idCommunity)
				if (result.toString == "[]") {
					res.status(404).json([{ "ERROR": "Commmunity not found" }]);
				} else {
					res.status(200).json(result);
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


router.post('/stats', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				const nbUsers = await community.getNbUsers(req.body.idCommunity);

				const totalPointsCommunity = await community.getTotalPointsCommunity(req.body.idCommunity);

				const nbPosts = await community.getNbPosts(req.body.idCommunity);

				const nbVotes = await community.getNbVotes(req.body.idCommunity);

				// TODO : BUILD RESPONSE
				res.status(200).json([{
					"nbUsers": nbUsers.nbUsers,
					"totalPointsCommunity": parseInt(totalPointsCommunity.totalPointsCommunity),
					"nbPosts": nbPosts.nbPost,
					"nbVotes": nbVotes.nbVote,
				}]);

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


router.post('/topChoices', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await community.getTopChoicesVotes(req.body.idCommunity));
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





router.get('/user/:idUser', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await community.getUsersCommunity(req.params.idUser));
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





router.get('/:id', async function (req, res, next) {
	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				const result = await community.getCommunityInfo(req.params.id)
				if (result.toString == "[]") {
					res.status(404).json([{ "ERROR": "Commmunity not found" }]);
				} else {
					//res.status(400).json([{ "ERROR": err.message }]);
					res.status(200).json(await community.getCommunityInfo(req.params.id));
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
