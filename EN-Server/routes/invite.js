const express = require('express');
const router = express.Router();
const invite = require('../services/invite.js');
//const jwtUtils = require('../utils/jwt.utils.js');

router.post('/create', async function (_, res) {

	//await invite.insertInvite();
});


/* GET userInfo // idUser must be in body request*/
router.get('/all', async function (req, res, next) {
	try {
		res.json(await invite.getAllInvites());
	} catch (err) {
		console.error(`Error while getting invites `, err.message);
		next(err);
	}
});

module.exports = router;
