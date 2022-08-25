
const adminLoginSecure = require("./adminLoginSecure.js")
const updateAdminPassword = require('./updateAdminPassword.js');
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/admin/loginSecure": {
            ...adminLoginSecure,
        },
        "/admin/password/reset": {
            ...updateAdminPassword
        }
    },
    schema: {
        ...schema,
    },
};
