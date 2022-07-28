const getAll = require("./getAll.js")
const getAllByPoints = require("./getAllByPoints.js")
const getInfos = require("./getInfos.js")
const getLogin = require("./getLogin.js")
const getReportedUsers = require("./getReportedUsers.js")
const postRegister = require("./postRegister.js")
const updateUser = require("./updateUser.js")
const updatePassword = require("./updatePassword.js")
const deleteUser = require("./deleteUser.js")
const lastRegisteredUsers = require('./lastRegisteredUsers.js')
const registerAdmin = require('./registerAdmin.js');
const addToCommunity = require('./addToCommunity.js');
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/user/all/{idCommunity}": {
			...getAll,
		},
		"/user/infos": {
			...getInfos,
		},
		"/user/login": {
			...getLogin,
		},
		"/user/register": {
			...postRegister,
		},
		"/user/infos/update": {
			...updateUser,
		},
		"/user/all/reports": {
			...getReportedUsers,
		},
		"/user/all/points": {
			...getAllByPoints,
		},
		"/user/password/reset": {
			...updatePassword,
		},
		"/user/delete/{idUser}": {
			...deleteUser,
		},
		"/user/lastRegistered/{idCommunity}": {
			...lastRegisteredUsers,
		},
		"/user/register/admin": {
			...registerAdmin,
		},
		"/user/addToCommunity": {
			...addToCommunity,
		}
	},
	schema: {
		...schema,
	},
};
