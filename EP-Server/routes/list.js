
const express = require('express');
const router = express.Router();
const list = require('../services/list.js');

router.get('/all/:idUser', async function (req, res, next) {
    try {
        res.status(200).json(await list.getListOfUser(req.params.idUser));
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

module.exports = router;