const getVoteListByCommunity = require("./getVoteListByCommunity.js")
const getVoteinfo = require("./getVoteinfo.js")
const voteListAndroid = require("./voteListAndroid.js");
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/vote/voteList": {
			...getVoteListByCommunity,
		},
		"/vote/voteInfo": {
			...getVoteinfo,
		},
		"/vote/voteListAndroid": {
			...voteListAndroid,
		},
	},
	schema: {
		...schema,
	},
};
