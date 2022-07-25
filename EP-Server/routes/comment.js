
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

router.post('/create', async function (req, res, next) {
    try {
        await comment.inserComment(req.body);
        res.status(200).json({ "Message": "Comment created successfully" });
    } catch (err) {
        res.status(400).json({ "ERROR": err.message });
        next(err);
    }
});

module.exports = router;
