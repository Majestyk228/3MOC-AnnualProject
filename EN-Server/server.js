//importations
var express = require('express');

// CORS
var cors = require('cors');

// File-Morgan


//instanciation du serveur
var server = express();

// INSTANCIATION DES LOG DE REQUEST ET REPONSES



//importation des routes
const userRouter = require("./routes/user.js");
const postRouter = require("./routes/post.js");
const inviteRouter = require("./routes/invite.js");
const communityRouter = require("./routes/community.js");
const adminRouter = require("./routes/admin.js");
const rewardRouter = require("./routes/rewards.js");
const commentRouter = require("./routes/comment.js");
const voteRouter = require("./routes/vote.js");
////const mysql = require('mysql');
const bodyParser = require('body-parser');
const swaggerUI = require('swagger-ui-express');
const docs = require('./docs/index.js');



//Body Parser
server.use(bodyParser.json());

// CORS
server.use(cors());


//Middlewares
server.use('/user', userRouter);
server.use('/post', postRouter);
server.use('/invite', inviteRouter);
server.use('/community', communityRouter);
server.use('/admin', adminRouter);
server.use('/rewards', rewardRouter);
server.use('/comment', commentRouter);
server.use('/vote', voteRouter);

server.use("/swagger", swaggerUI.serve, swaggerUI.setup(docs));






//lancement du serveur sur le port 8080
server.listen(8080, function () {
	//console.log("/!\\ Serveur en écoute sur le port 8080 /!\\");

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
