const express = require('express');
const router = express.Router();
const invite = require('../services/invite.js');
//const jwtUtils = require('../utils/jwt.utils.js');







/*POST create invite -> idCommunity must be in body*/
router.post('/create', async function (req, res, next) {

	try {
		const createInvite = await invite.insertInvite(req.body);
		const lastInvite = await invite.getLastInvite(req.body.idCommunity);

		res.status(200).json({ "message": createInvite.message, "code": lastInvite[0].code });
	} catch (err) {
		res.status(400).json({ "ERROR": "Bad Request" });
		next(err);
	}
});


/* GET allInvites */
router.get('/all', async function (_, res, next) {
	try {
		res.json(await invite.getAllInvites());
	} catch (err) {
		console.error(`Error while getting invites `, err.message);
		next(err);
	}
});

module.exports = router;
