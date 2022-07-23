
const express = require('express');
const router = express.Router();
const task = require('../services/task.js');

router.get('/taskInfo/:idTask', async function (req, res, next) {
    try {
        res.status(200).json(await task.getTask(req.params.idTask));
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

module.exports = router;