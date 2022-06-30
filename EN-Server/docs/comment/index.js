const nbReportedCommentsAll = require("./nbReportedCommentsAll.js")
const schema = require("./_model.js")
module.exports = {
	paths: {
		"/nbReportedCommentsAll/{idCommunity}": {
			...nbReportedCommentsAll,
		}
	},
	schema: {
		...schema,
	},
};
