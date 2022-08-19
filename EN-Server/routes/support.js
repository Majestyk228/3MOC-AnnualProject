const express = require('express');
const { route } = require('express/lib/application');
const router = express.Router();
const support = require('../services/support.js');



/* POST send message to given mail */
router.post('/', async function (req, res, next) {

	try {
		if (req.headers.token) {
			// VERIFY TOKEN
			try {
				// IF TOKEN IS VALID
				const decoded = jwt.verify(req.headers.token, config.JWT_SIGN_SECRET)

				await support.sendMail2(req.body);
				res.status(200).json({ "Message": "Message successfully sent to support service." });
			} catch (err) {
				// IF TOKEN IS INVALID
				res.status(406).json([{ "ERROR": "Token expired/incorrect" }]);
			}
		} else {
			res.status(404).json([{ "ERROR": "Missing token in header" }]);
		}
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});

module.exports = router;
