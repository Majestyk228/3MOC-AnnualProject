const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const comment = require('../services/comment.js');


/* GET nbComment*/
router.get('/allcount', async function (_, res, next) {
	try {
		res.status(200).json(await comment.getnbComment());
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});




/* GET reported comment on 1 post*/
router.get('/reported/:idPost', async function (req, res, next) {
	try {
		res.status(200).json(await comment.getReportedComments(req.params.idPost));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


/* GET nbComment on 1 post*/
router.get('/count/:idPost', async function (req, res, next) {
	try {
		if (!req.params.idPost) {
			res.status(400).json([{ "ERROR": "Missing parameter" }]);
		} else {
			res.status(200).json(await comment.getNbCommentsPerPost(req.params.idPost));
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



/* GET nbReportedComment on 1 post*/
router.get('/reported/count/:idPost', async function (req, res, next) {
	try {
		if (!req.params.idPost) {
			res.status(400).json([{ "ERROR": "Missing parameter" }]);
		} else {
			res.status(200).json(await comment.getNbReportedCommentsPerPost(req.params.idPost));
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


/* GET nbReportedComment*/
router.get('/allcount/reported', async function (_, res, next) {
	try {
		res.status(200).json(await comment.getNbReportedComments());
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



/* GET all comments on 1 post*/
router.get('/all/:idPost', async function (req, res, next) {
	try {
		res.status(200).json(await comment.getComments(req.params.idPost));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});


// TODO Post comment !

router.post('/create', async function (req, res, next) {
	if (req.body.idCommunity == null || req.body.body == null || req.body.anonymous == null || req.body.idPost == null || req.body.idUser == null) {
		//missing infos
		res.status(400).json([{ "ERROR": "Missing info(s)" }]);
	} else {
		//call function
		try {
			await comment.insertComment(req.body);
			res.status(201).json([{ "MESSAGE": "Comment Added Successfully" }]);

		} catch (err) {
			res.status(400).json([{ "ERROR": "Bad Request" }]);
			next(err);
		}
	}
});


module.exports = router;
