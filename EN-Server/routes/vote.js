const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const vote = require('../services/vote.js');
//const jwtUtils = require('../utils/jwt.utils.js');



/* GET VoteListByCommunity idCommunity MUST BE IN BODY*/
router.get('/voteList', async function (req, res, next) {
	try {
		res.status(200).json(await vote.getVoteListByCommunity(req.body.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});





module.exports = router;
