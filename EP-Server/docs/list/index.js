const getListOfUser = require("./getListOfUser.js");
const create = require('./create.js');
const deleteList = require('./delete.js');
const update = require('./update.js');
const schema = require("./_model.js");
module.exports = {
    paths: {
        "/list/{idUser}": {
            ...getListOfUser,
        },
        "/list/create": {
            ...create,
        },
        "/list/delete/{idList}": {
            ...deleteList,
        },
        "/list/update": {
            ...update,
        },
    },
    schema: {
        ...schema,
    },
};
