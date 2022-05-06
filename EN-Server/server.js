//importations
var express = require('express');

//instanciation du serveur
var server = express();
const userRouter = require("./routes/user.js");
const postRouter = require("./routes/post.js");
const inviteRouter = require("./routes/invite.js");
const communityRouter = require("./routes/community.js");
const mysql = require('mysql');
const bodyParser = require('body-parser');

server.use(bodyParser.json());
server.use('/user', userRouter);
server.use('/post', postRouter);
server.use('/invite', inviteRouter);
server.use('/community', communityRouter);







//routes user Android
server.get('/', function (req, res) {
	res.setHeader('Content-Type', 'text/html');
	res.status(200).send('<h1>Hello, Thanos did nothing wrong !</1>');
});







//lancement du serveur sur le port 8080
server.listen(8080, function () {
	console.log("/!\\ Serveur en écoute sur le port 8080 /!\\");

	/*var connection = mysql.createConnection({
		host: 'exprimonsnous.coqdgtazlflp.us-east-1.rds.amazonaws.com',
		user: 'admin',
		password: 'kB9qG7e3zEU3',
		database: 'exprimonsNous'
	});

	connection.connect();

	connection.query('SHOW TABLES', function (err, rows, fields) {
		if (err) throw err;
		console.log('The solution is: ', rows);
	});

	connection.end();*/
})
