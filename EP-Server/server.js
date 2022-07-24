//importations
var express = require('express');



//instanciation du serveur
var server = express();
const port = process.env.PORT || 3000

// INSTANCIATION DES LOG DE REQUEST ET REPONSES

// IMPORTS
const bodyParser = require('body-parser');



//importation des routes
const commentRouter = require("./routes/comment.js");
const taskRouter = require("./routes/task.js");
const listRouter = require("./routes/list.js");
const tagRouter = require("./routes/tag.js");
const userRouter = require("./routes/user.js");

// SWAGGER
const swaggerUI = require('swagger-ui-express');
const docs = require('./docs/index.js');


//Body Parser
server.use(bodyParser.json());


//importation des routes
//const userRouter = require("./routes/user.js");

//Middlewares
server.use('/comment', commentRouter);
server.use('/task', taskRouter);
server.use('/list', listRouter);
server.use('/tag', tagRouter);
server.use('/user', userRouter);

// SWAGGER SETUP
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

//lancement du serveur sur le port 8080
server.listen(port, function () {
    console.log("/!\\ Serveur en écoute sur le port 8080 /!\\");
});
