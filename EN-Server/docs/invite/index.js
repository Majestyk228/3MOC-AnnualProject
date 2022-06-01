const getAllInvite = require("./getAll.js")
const createInvite = require("./create.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/invite/all": {
			...getAllInvite,
		},
		"/invite/create": {
			...createInvite,
		}
	},
	schema: {
		...schema,
	},
};
