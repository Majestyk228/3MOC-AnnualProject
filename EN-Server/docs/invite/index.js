const getAllInvite = require("./getAll.js")
const createInvite = require("./create.js")
const getAllByCommunity = require('./getAllByCommunity.js')
const deleteInvite = require('./deleteInvite.js');
const getCommunity = require('./getCommunity.js');
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/invite/all": {
			...getAllInvite,
		},
		"/invite/getCommunity/{code}": {
			...getCommunity,
		},
		"/invite/allByCommunity/{idCommunity}": {
			...getAllByCommunity,
		},
		"/invite/create": {
			...createInvite,
		},
		"/invite/delete/{code}": {
			...deleteInvite,
		},
	},
	schema: {
		...schema,
	},
};
