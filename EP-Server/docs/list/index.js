const getListOfUser = require("./getListOfUser.js");
const create = require('./create.js');
const schema = require("./_model.js");
module.exports = {
    paths: {
        "/list/{idUser}": {
            ...getListOfUser,
        },
        "/list/create": {
            ...create,
        },
    },
    schema: {
        ...schema,
    },
};
