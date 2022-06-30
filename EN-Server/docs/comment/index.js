const nbReportedCommentsAll = require("./nbReportedCommentsAll.js")
const commentByPost = require('./commentByPost.js')
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/nbReportedCommentsAll/{idCommunity}": {
			...nbReportedCommentsAll,
		},
		"/all/{idPost}": {
			...commentByPost,
		}
	},
	schema: {
		...schema,
	},
};
