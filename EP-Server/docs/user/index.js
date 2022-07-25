const login = require("./login.js")
const getUserInfo = require("./getuserInfos.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/user/login": {
			...login,
		},
		"user/userInfos/{idUser}": {
			...getUserInfo
		}
	},
	schema: {
		...schema,
	},
};
