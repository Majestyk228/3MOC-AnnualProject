
const express = require('express');
const router = express.Router();
const tag = require('../services/tag.js');

router.get('/all', async function (_, res, next) {
    try {
        res.status(200).json(await tag.getAllTag());
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

module.exports = router;