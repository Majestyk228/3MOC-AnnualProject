const nbReportedCommentsAll = require("./nbReportedCommentsAll.js")
const commentByPost = require('./commentByPost.js')
const reportedCommentByPost = require('./reportedCommentsByPost.js');
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/comment/nbReportedCommentsAll/{idCommunity}": {
			...nbReportedCommentsAll,
		},
		"/comment/all/{idPost}": {
			...commentByPost,
		},
		"/comment/reported/{idPost}": {
			...reportedCommentByPost,
		}
	},
	schema: {
		...schema,
	},
};
