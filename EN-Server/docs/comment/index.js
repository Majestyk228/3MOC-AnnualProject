const allcount = require('./allcount.js')
const nbReportedCommentsAll = require("./nbReportedCommentsAll.js")
const commentByPost = require('./commentByPost.js')
const countByPost = require('./countByPost.js');
const countReportedByPost = require('./countReportedByPost.js');
const reportedCommentByPost = require('./reportedCommentsByPost.js');
const allcountReported = require('./allcountReported.js');
const create = require('./create.js');
const countReportedByCommunity = require('./countReportedByCommunity.js');
const deleteCommment = require('./delete.js');
const report = require('./report.js');
const reportInit = require('./reportInit.js');
const formattedComment = require('./formattedComments.js');
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/comment/allcount": {
			...allcount,
		},
		"/comment/reported/{idPost}": {
			...reportedCommentByPost,
		},
		"/comment/count/{idPost}": {
			...countByPost,
		},
		"/comment/reported/count/{idPost}": {
			...countReportedByPost,
		},
		"/comment/nbReportedCommentsAll/{idCommunity}": {
			...nbReportedCommentsAll,
		},
		"/comment/all/{idPost}": {
			...commentByPost,
		},
		"/comment/allcount/reported": {
			...allcountReported,
		},
		"/comment/create": {
			...create,
		},
		"/comment/nbReportedCommentsAll/{idCommunity}": {
			...countReportedByCommunity
		},
		"/comment/delete/{idComment}": {
			...deleteCommment
		},
		"/comment/report/{idComment}": {
			...report
		},
		"/comment/reportReinit/{idComment}": {
			...reportInit
		},
		"/comment/formattedComment/{idPost}": {
			...formattedComment
		}
	},
	schema: {
		...schema,
	},
};
