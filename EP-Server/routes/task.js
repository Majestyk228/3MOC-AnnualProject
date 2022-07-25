
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

router.post('/create', async function (req, res, next) {
    try {
        await task.insertTask(req.body)
        res.status(200).json({ "Message": "Task created successfully." });
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

router.delete('/delete/:idTask', async function (req, res, next) {
    try {
        await task.deleteTask(req.params.idTask)
        res.status(200).json({ "Message": "Task deleted successfully." });
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

router.get('/tasks/:idList', async function (req, res, next) {
    try {
        res.status(200).json(await task.getTasksFromList(req.params.idList));
    } catch (err) {
        res.status(400).json([{ "ERROR": err.message }]);
        next(err);
    }
});

module.exports = router;
