const login = require("./login.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/user/login": {
			...login,
		}
	},
	schema: {
		...schema,
	},
};
