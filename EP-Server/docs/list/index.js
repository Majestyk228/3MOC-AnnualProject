const getListOfUser = require("./getListOfUser.js");
const create = require('./create.js');
const deleteList = require('./delete.js');
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
    },
    schema: {
        ...schema,
    },
};
