const getListOfUser = require("./getListOfUser.js")
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/list/{idUser}": {
            ...getListOfUser,
        }
    },
    schema: {
        ...schema,
    },
};