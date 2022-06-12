const getCommunityStats = require("./getCommunityStats.js")
const getTopChoicesVotes = require("./getTopChoicesVotes.js")
const getCommunityInfo = require("./getCommunityInfo.js");
const getUsersCommunity = require("./getUsersCommunity.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/community/stats": {
			...getCommunityStats,
		},
		"/community/topChoices": {
			...getTopChoicesVotes,
		},
		"/community/{idCommunity}": {
			...getCommunityInfo,
		},
		"/community/user{idUser}": {
			...getUsersCommunity,
		}
	},
	schema: {
		...schema,
	},
};
