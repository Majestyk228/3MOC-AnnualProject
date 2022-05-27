const getAllInvite = require("./getAll.js")
const createInvite = require("./create.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/invite": {
			...getAllInvite
		},
		"/create": {
			...createInvite
		}
	},
	schema: {
		...schema,
	},
};
