const express = require('express');
const router = express.Router();
const user = require('../services/user.js');

/* GET allUsers*/
router.get('/all', async function (_, res, next) {
	try {
		res.json(await user.getAllUsers());
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});

/* GET userInfo // idUser must be in body request*/
router.get('/infos', async function (req, res, next) {
	try {
		res.json(await user.getUserInfo(req.body.idUser));
	} catch (err) {
		console.error(`Error while getting users `, err.message);
		next(err);
	}
});

module.exports = router;
