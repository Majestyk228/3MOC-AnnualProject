const getVoteListByCommunity = require("./getVoteListByCommunity.js")
const getVoteinfo = require("./getVoteinfo.js")
const voteListAndroid = require("./voteListAndroid.js");
const createVote = require("./createVote.js")
const deleteVote = require('./deleteVote.js')
const lastVote = require('./lastVote');
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
		"/vote/create": {
			...createVote,
		},
		"/vote/delete/{idVote}": {
			...deleteVote,
		},
		"/vote/lastPost/{idCommunity}": {
			...lastVote,
		},
	},
	schema: {
		...schema,
	},
};
