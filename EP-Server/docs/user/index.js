const login = require("./login.js")
const getUserInfo = require("./getuserInfos.js")
const update = require('./update.js')
const updatePassword = require('./updatePassword.js')
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/user/login": {
			...login,
		},
		"user/userInfos/{idUser}": {
			...getUserInfo
		},
		"user/update": {
			...update
		},
		"user/update/password": {
			...updatePassword
		}
	},
	schema: {
		...schema,
	},
};
