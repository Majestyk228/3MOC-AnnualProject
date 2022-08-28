const getVoteListByCommunity = require("./getVoteListByCommunity.js")
const getVoteinfo = require("./getVoteinfo.js")
const voteListAndroid = require("./voteListAndroid.js");
const createVote = require("./createVote.js")
const deleteVote = require('./deleteVote.js')
const lastVote = require('./lastVote');
const updateVote = require('./updateVote.js');
const voteOptions = require('./voteOptions.js');
const infos = require('./infos.js');
const newVoteOptions = require('./newVoteOptions.js');
const userVote = require("./userVote.js");
const schema = require("./_model.js");
module.exports = {
	paths: {
		"/vote/voteList/{idCommunity}": {
			...getVoteListByCommunity,
		},
		"/vote/lastPost/{idCommunity}": {
			...lastVote,
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
		"/vote/options": {
			...voteOptions
		},
		"/vote/infos": {
			...infos
		},
		"/vote/newVoteOptions": {
			...newVoteOptions
		},
		"/vote/userVote": {
			...userVote
		},
		"/vote/updateVote": {
			...updateVote
		},
		"/vote/delete/{idVote}": {
			...deleteVote,
		},
	},
	schema: {
		...schema,
	},
};
