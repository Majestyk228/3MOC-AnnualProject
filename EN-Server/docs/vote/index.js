const getVoteListByCommunity = require("./getVoteListByCommunity.js")
const getVoteinfo = require("./getVoteinfo.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/vote/voteList": {
			...getVoteListByCommunity,
		},
		"/vote/voteInfo": {
			...getVoteinfo,
		},
	},
	schema: {
		...schema,
	},
};
