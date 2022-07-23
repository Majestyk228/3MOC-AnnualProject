
const express = require('express');
const router = express.Router();
const comment = require('../services/comment.js');

router.get('/all/:idTask', async function (req, res, next) {
    try {
        res.status(200).json(await comment.getAllCommentsFromTask(req.params.idTask));
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

module.exports = router;