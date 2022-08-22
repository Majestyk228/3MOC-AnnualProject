const express = require('express');
const router = express.Router();
const reward = require('../services/rewards.js');
const post = require('../services/post.js');
const config = require('../config/config.js');
const pm = require('../services/pointsManager.js');
var jwt = require('jsonwebtoken');





/* GET post*/
router.get('/all', async function (_, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await reward.getAllRewards());
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

/* GET nbRewards on 1 post */
router.get('/nbReward/:idPost', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				res.status(200).json(await reward.nbRewardByPost(req.params.idPost));
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

/* POST user Reward*/
router.post('/useReward', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await reward.useReward(req.body);
				res.status(200).json({ "Message": "Reward added successfully." });
			} catch (err) {
				// IF TOKEN IS INVALID
				res.status(406).json([{ "ERROR": "Token expired/incorrect" }]);
			}

			// TODO : GET POST THAT GOT REWARDED TO RETREIVE ITS OWNER
			const postTargeted = await post.getPost(req.body.idPost)
			const rewardTargeted = await reward.getRewardPointsValues(req.body.idRewards)

			pm.addPoints(postTargeted[0].idUser, rewardTargeted)


		} else {
			res.status(404).json([{ "ERROR": "Missing token in header" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

module.exports = router;
