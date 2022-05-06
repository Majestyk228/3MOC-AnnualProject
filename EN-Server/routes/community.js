const express = require('express');
const router = express.Router();
const community = require('../services/community.js');
//const jwtUtils = require('../utils/jwt.utils.js');

router.get('/:id', async function (req, res, next) {
	try {
		res.json(await community.getCommunityInfo(req.params.id));
	} catch (err) {
		console.error(`Error while getting community `, err.message);
		next(err);
	}
});

module.exports = router;
