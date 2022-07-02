const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const vote = require('../services/vote.js');
//const jwtUtils = require('../utils/jwt.utils.js');



/* GET VoteListByCommunity idCommunity MUST BE IN BODY*/
router.get('/voteList/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await vote.getVoteListByCommunity(req.params.idCommunity));
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


// CREATE a vote
router.post('/create', async function (req, res, next) {
	try {
		await vote.createVote(req.body);
		res.status(200).json({ "Message": "Vote created successfully." });
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


// DELETE a vote
router.delete('/delete/:idVote', async function (req, res, next) {
	try {
		await vote.deleteVote(req.params.idVote);
		res.status(200).json({ "Message": "Vote deleted successfully." });
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



router.get('/lastVotes/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await vote.lastPosts(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


// UPDATE A vote
router.put('/updateVote', async function (req, res, next) {
	//TODO : code
	try {
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
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
})



module.exports = router;
