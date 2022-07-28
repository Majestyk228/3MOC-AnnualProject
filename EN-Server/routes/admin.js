const express = require('express');
const router = express.Router();
const admin = require('../services/admin.js');






router.get('/all', async function (_, res, next) {

	try {
		res.status(200).json(await admin.getAllAdmin());
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});








// email must be in body request
router.post('/login', async function (req, res, next) {
	try {
		res.status(200).json(await admin.getAdminCredentials(req.body.email));
	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});


// email must be in body request
/*router.post('/login', async function (req, res, next) {
	try {
		const adminCredentials = 
		res.status(200).json(await admin.getAdminCredentials(req.body.email));

		if (bcrypt.compareSync(req.body.password, userCredentials[0].password) == false) {
			res.status(403).json({ 'ERROR': "incorrect password" });
			next();
		}


	} catch (err) {
		res.status(400).json([{ "ERROR": err.message }]);
		next(err);
	}
});*/

module.exports = router;
