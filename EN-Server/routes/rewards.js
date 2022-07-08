const express = require('express');
const router = express.Router();
const reward = require('../services/rewards.js');

/* GET post*/
router.get('/all', async function (_, res, next) {
	try {
		res.status(200).json(await reward.getAllRewards());
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});

/* GET nbRewards on 1 post */
router.get('/nbReward/:idPost', async function (req, res, next) {
	try {
		res.status(200).json(await reward.nbRewardByPost(req.params.idPost));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});

module.exports = router;
