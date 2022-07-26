
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


router.post('/create', async function (req, res, next) {
    try {
        await list.insertList(req.body);
        res.status(200).json({ "Message": "List created successfully" });
    } catch (err) {
        res.status(400).json({ "ERROR": err.message });
        next(err);
    }
});

router.delete('/delete/:idList', async function (req, res, next) {
    try {
        await list.deleteList(req.params.idList);
        res.status(200).json({ "Message": "List deleted successfully" });
    } catch (err) {
        res.status(400).json({ "ERROR": err.message });
        next(err);
    }
});

router.put('/update', async function (req, res, next) {
    try {
        // Récupération des champs de la requete
        const newTitle = req.body.title;
        // Récupération des champs en BDD
        const oldTitle = await list.getList(req.body.idList);
        console.log(oldTitle)

        // Si champ vide -> mettre champ BDD à la place
        if (newTitle == null) {
            res.status(404).json({ "ERROR": "Missing title argument" });
        } else {
            await list.updateList(req.body.idList, newTitle);
        }
        res.status(200).json({ "Message": "List updated successfully" });
    } catch (err) {
        res.status(400).json({ "ERROR": err.message });
        next(err);
    }
})

module.exports = router;
