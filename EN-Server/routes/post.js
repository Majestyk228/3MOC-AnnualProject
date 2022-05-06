const express = require('express');
const router = express.Router();
const post = require('../services/post.js');
////const jwtUtils = require('../utils/jwt.utils.js');

/* GET allPosts*/
router.get('/all', async function (_, res, next) {
	try {
		res.json(await post.getAllPosts());
	} catch (err) {
		console.error(`Error while getting posts `, err.message);
		next(err);
	}
});


/* GET post*/
router.get('/all/:id', async function (req, res, next) {
	try {
		res.json(await post.getPost(req.params.id));
	} catch (err) {
		console.error(`Error while getting posts `, err.message);
		next(err);
	}
});


module.exports = router;
