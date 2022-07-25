const commentsFromTask = require("./commentsFromTask.js");
const createComment = require("./create.js");
const deleteComment = require("./delete.js");
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/comment/all/{idTask}": {
            ...commentsFromTask,
        },
        "/comment/create": {
            ...createComment,
        },
        "/comment/delete/{idComment}": {
            ...deleteComment,
        },
    },
    schema: {
        ...schema,
    },
};
