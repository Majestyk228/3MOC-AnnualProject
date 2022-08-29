// IMPORT EXPRESS
var express = require('express');

// CORS
var cors = require('cors');

// INSTANCIATE SERVER
var server = express();


// NOTIFICATION PARAMETERS
/*const notification_options = {
	priority: "high",
	timeToLive: 60 * 60 * 24
}*/


// IMPORT ALL ROUTES
const userRouter = require("./routes/user.js");
const postRouter = require("./routes/post.js");
const inviteRouter = require("./routes/invite.js");
const communityRouter = require("./routes/community.js");
const adminRouter = require("./routes/admin.js");
const rewardRouter = require("./routes/rewards.js");
const commentRouter = require("./routes/comment.js");
const voteRouter = require("./routes/vote.js");
const supportRouter = require("./routes/support.js");
const bodyParser = require('body-parser');
const swaggerUI = require('swagger-ui-express');
const docs = require('./docs/index.js');



// BODY PARSER
server.use(bodyParser.json());

// CORS
server.use(cors());


// MIDDLEWARES
server.use('/user', userRouter);
server.use('/post', postRouter);
server.use('/invite', inviteRouter);
server.use('/community', communityRouter);
server.use('/admin', adminRouter);
server.use('/rewards', rewardRouter);
server.use('/comment', commentRouter);
server.use('/vote', voteRouter);
server.use('/support', supportRouter);





// SWWAGGER SETUP

// REMOVING THE TRY NOW BUTTON
const DisableTryItOutPlugin = function () {
	return {
		statePlugins: {
			spec: {
				wrapSelectors: {
					allowTryItOutFor: () => () => false
				}
			}
		}
	}
}

const options = {
	swaggerOptions: {
		plugins: [
			DisableTryItOutPlugin
		]
	}
};


server.use("/swagger", swaggerUI.serve, swaggerUI.setup(docs, options));







// LAUNCH SERVER ON PORT 8080
server.listen(8080, function () {
	console.log("/!\\ Serveur en écoute sur le port 8080 /!\\");
})
