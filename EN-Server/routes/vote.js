const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const vote = require('../services/vote.js');
//const jwtUtils = require('../utils/jwt.utils.js');



/* GET VoteListByCommunity idCommunity MUST BE IN BODY*/
router.post('/voteList', async function (req, res, next) {
	try {
		res.status(200).json(await vote.getVoteListByCommunity(req.body.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



/* GET Vote's infos, their choices and the number of time each choices has been selected MUST BE IN BODY*/
router.post('/voteInfo', async function (req, res, next) {
	try {
		//TODO : take title and body
		const titleBody = await vote.getVoteTitleBody(req.body.idVote)

		//TODO : Take choices list
		const choiceList = await vote.getVoteOptions(req.body.idVote)

		//TODO : Take number for selection for each choice

		var nbSelected = []

		choiceList.forEach(choice => {
			nbSelected.push(vote.getListOfNumber(req.body.idVote, choice)).value
		})

		//console.log(nbSelected)

		res.status(200).json(
			[
				{
					"title": titleBody[0].title,
					"body": titleBody[0].body,
					"voteOptions": choiceList,
					"nbChoiceVoteOptions": nbSelected
				}
			]
		);
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



/* GET VoteListByCommunityAndroid idCommunity MUST BE IN BODY*/
router.post('/voteListAndroid', async function (req, res, next) {
	try {
		res.status(200).json(await vote.getVoteListByCommunityAndroid(req.body.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


router.post('/create', async function (req, res, next) {
	try {
		await vote.createVote(req.body);
		res.status(200).json({ "Message": "Vote created successfully." });
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


router.delete('/delete/:idVote', async function (req, res, next) {
	try {
		await vote.deleteVote(req.params.idVote);
		res.status(200).json({ "Message": "Vote deleted successfully." });
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



module.exports = router;
