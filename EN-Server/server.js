//importations
var express = require('express');

//instanciation du serveur
var server = express();

//configuration des routes autorisées
    //structure générale d'une route
    /*
        server.get('/',function(req,res) {
            //code...
        });
    */

    //routes user Android
    server.get('/',function(req,res) {
        res.setHeader('Content-Type','text/html');
        res.status(200).send('<h1>Hello, Thanos did nothing wrong');
    });

    //routes user iPad

    //routes user Flutter

//lancement du serveur
server.listen(8080, function() {
    console.log("/!\\ Serveur en écoute sur le port 8080 /!\\");
})