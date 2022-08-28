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
const all = require('./all.js');
const allReported = require('./allReported.js');
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
		"/comment/nbReportedCommentsAll/{idCommunity}": {
			...countReportedByCommunity
		},
		"/comment/report/{idComment}": {
			...report
		},
		"/comment/reportReinit/{idComment}": {
			...reportInit
		},
		"/comment/formattedComment/{idPost}": {
			...formattedComment
		},
		"/comment/all/{idCommunity}": {
			...all
		},
		"/comment/all/reported/{idCommunity}": {
			...allReported
		},
		"/comment/create": {
			...create,
		},
		"/comment/delete/{idComment}": {
			...deleteCommment
		},
	},
	schema: {
		...schema,
	},
};
