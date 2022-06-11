const getVoteListByCommunity = require("./getVoteListByCommunity.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/vote/voteList": {
			...getVoteListByCommunity,
		},
	},
	schema: {
		...schema,
	},
};
