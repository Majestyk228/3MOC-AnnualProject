const getAllAdmins = require("./getAll.js")
const adminLogin = require("./adminLogin.js")
const adminLoginSecure = require("./adminLoginSecure.js")
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/admin/all": {
            ...getAllAdmins,
        },
        "/admin/login": {
            ...adminLogin,
        },
        "/admin/loginSecure": {
            ...adminLoginSecure,
        }
    },
    schema: {
        ...schema,
    },
};
