const express = require('express');
const router = express.Router();
const invite = require('../services/invite.js');







/*POST create invite -> idCommunity must be in body*/
router.post('/create', async function (req, res, next) {

	try {
		const createInvite = await invite.insertInvite(req.body);
		const lastInvite = await invite.getLastInvite(req.body.idCommunity);

		res.status(200).json([{ "message": createInvite.message, "code": lastInvite[0].code }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});




/* GET allInvites */
router.get('/all', async function (_, res, next) {
	try {
		res.json(await invite.getAllInvites());
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});




/* GET allInvites per community */
router.get('/allByCommunity/:idCommunity', async function (req, res, next) {
	try {
		res.json(await invite.getAllCommunityInvites(req.params.idCommunity));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

router.delete('/delete/:code', async function (req, res, next) {
	try {
		await invite.deleteInvite(req.params.code);
		res.json([{ "Message": "Invitation deleted successfully" }]);
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});


module.exports = router;
