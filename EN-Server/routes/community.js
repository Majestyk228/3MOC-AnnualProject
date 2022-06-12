const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const community = require('../services/community.js');
//const jwtUtils = require('../utils/jwt.utils.js');



router.post('/stats', async function (req, res, next) {
	try {
		const result = await community.getCommunityStats(req.body.idCommunity)
		if (result.toString == "[]") {
			res.status(404).json([{ "ERROR": "Commmunity not found" }]);
		} else {
			//res.status(400).json([{ "ERROR": "Bad Request" }]);
			res.status(200).json(result);
		}

	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


router.post('/topChoices', async function (req, res, next) {
	try {
		res.status(200).json(await community.getTopChoicesVotes(req.body.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



router.get('/user/:idUser', async function (req, res, next) {
	try {
		res.status(200).json(await community.getUsersCommunity(req.params.idUser));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});





router.get('/:id', async function (req, res, next) {
	try {
		const result = await community.getCommunityInfo(req.params.id)
		console.log(result);
		if (result.toString == "[]") {
			res.status(404).json([{ "ERROR": "Commmunity not found" }]);
		} else {
			//res.status(400).json([{ "ERROR": "Bad Request" }]);
			res.status(200).json(await community.getCommunityInfo(req.params.id));
		}

	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});

module.exports = router;
