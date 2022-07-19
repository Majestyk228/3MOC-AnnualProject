const express = require('express');
const router = express.Router();
const post = require('../services/post.js');
const comment = require('../services/comment.js');
////const jwtUtils = require('../utils/jwt.utils.js');





/* GET allPosts formated to fit Exprimons-Nous Android App input*/
router.get('/formatted/:idCommunity', async function (req, res, next) {
	try {
		var posts = await post.getAllPostsFormatted(req.params.idCommunity);
		var newPosts = [];

		posts.forEach((post) => post.comments = 0);
		posts.forEach((post) => post.rewards = 0);

		newPosts = posts;

		res.status(200).send(JSON.stringify(newPosts));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});






/* GET allPosts*/
router.get('/all', async function (_, res, next) {
	try {
		res.status(200).json(await post.getAllPosts());
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});






/* GET post*/
router.get('/:id', async function (req, res, next) {
	try {
		res.status(200).json(await post.getPost(req.params.id));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});




/* GET nbReportedPosts */
router.get('/nbReportedPosts/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await post.nbReportedPosts(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});




/* POST createPost */
router.post('/create', async function (req, res, next) {
	try {
		await post.createPost(req.body);
		res.status(200).json({ "message": "Post created successfully" });
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});




router.get('/lastPosted/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await post.getLastPostedPosts(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}

});


// get reported posts
router.get('/reportedPosts/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await post.getAllReportedPosts(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}

});




// get reported posts
router.get('/all/:idCommunity', async function (req, res, next) {
	try {
		res.status(200).json(await post.getAllPostsByCommunity(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}

});






// UPDATE Admin post
router.put('/updatePost', async function (req, res, next) {
	try {
		if (!req.body.idPost) {
			res.status(422).json([{ "ERROR": "Missing argument(s)" }]);
		} else {

			var title = req.body.title;
			var body = req.body.body;

			var postDB = await post.getPost(req.body.idPost);

			if (title == null) {
				title = postDB[0].title;
			}

			if (body == null) {
				body = postDB[0].body;
			}

			await post.updatePost(req.body.idPost, title, body);
			res.status(200).json([{ "Message": "Post updated successfully" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}

});


// get reported posts
router.delete('/delete/:idPost', async function (req, res, next) {
	try {
		await post.deletePost(req.params.idPost);
		res.status(200).json([{ "Message": "Post deleted successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}

});

router.post('/like', async function (req, res, next) {
	// TODO CODE
	try {
		await post.likePost(req.body.idPost);
		res.status(200).json([{ "Message": "Post liked successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});


router.post('/dislike', async function (req, res, next) {
	// TODO CODE
	try {
		await post.dislikePost(req.body.idPost);
		res.status(200).json([{ "Message": "Post disliked successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});



router.get('/report/:idPost', async function (req, res, next) {
	// TODO CODE
	try {
		await post.reportPost(req.params.idPost);
		res.status(200).json([{ "Message": "Post reported successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});



router.get('/reportReinit/:idPost', async function (req, res, next) {
	// TODO CODE
	try {
		await post.reportPostReinit(req.params.idPost);
		res.status(200).json([{ "Message": "Post reinitialized successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});
module.exports = router;
