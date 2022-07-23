const getTaskInfo = require("./getTaskInfo.js")
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/task/{idTask}": {
            ...getTaskInfo,
        }
    },
    schema: {
        ...schema,
    },
};