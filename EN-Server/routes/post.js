const express = require('express');
const router = express.Router();
const post = require('../services/post.js');
////const jwtUtils = require('../utils/jwt.utils.js');



/* GET allPosts formated to fit Exprimons-Nous Android App input*/
router.get('/formatted/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await post.getAllPostsFormatted(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		//console.error(`Error while getting posts `, err.message);
		next(err);
	}
});

/* GET allPosts*/
router.get('/all', async function (_, res, next) {
	try {
		res.status(200).json(await post.getAllPosts());
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		//console.error(`Error while getting posts `, err.message);
		next(err);
	}
});



/* GET post*/
router.get('/:id', async function (req, res, next) {
	try {
		res.status(200).json(await post.getPost(req.params.id));
	} catch (err) {
		res.status(400).json([{ "ERROR": "Bad Request" }]);
		next(err);
	}
});



module.exports = router;
