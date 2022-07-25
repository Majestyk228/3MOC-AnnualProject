const commentsFromTask = require("./commentsFromTask.js")
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/comment/all/{idTask}": {
            ...commentsFromTask,
        }
    },
    schema: {
        ...schema,
    },
};
