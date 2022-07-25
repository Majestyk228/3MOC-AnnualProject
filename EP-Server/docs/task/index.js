const getTaskInfo = require("./getTaskInfo.js");
const create = require("./create.js");
const deleteTask = require("./delete.js");
const tasksFromList = require('./tasksFromList.js');
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/task/{idTask}": {
            ...getTaskInfo,
        },
        "/task/tasks/{idList}": {
            ...tasksFromList,
        },
        "/task/create": {
            ...create,
        },
        "/task/delete/{idTask}": {
            ...deleteTask,
        },
    },
    schema: {
        ...schema,
    },
};
