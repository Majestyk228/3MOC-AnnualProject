const getAllInvite = require("./getAll.js")
const createInvite = require("./create.js")
const getAllByCommunity = require('./getAllByCommunity.js')
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/invite/all": {
			...getAllInvite,
		},
		"/invite/create": {
			...createInvite,
		},
		"/invite/getAllByCommunity/{idCommunity}": {
			...getAllByCommunity,
		}
	},
	schema: {
		...schema,
	},
};
