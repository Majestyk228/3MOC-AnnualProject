const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const support = require('../services/support.js');

/* POST send message to given mail */
router.post('/', async function (req, res, next) {
	try {
		await support.sendMail(req.body);
		res.status(200).json({ "Message": "Message successfully sent to support service." });
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

module.exports = router;
