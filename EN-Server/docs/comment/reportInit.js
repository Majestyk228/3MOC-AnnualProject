module.exports = {
	get: {
		tags: ["comment"],
		description: "Reinitializes the number of reports for a given comment.",
		operationId: "reportCommentInit",
		parameters: [
			{
				in: "path",
				name: "idCommunity",
				required: true,
				description: "ID of the comment",
				schema: {
					type: "integer",
					example: {
						idComment: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Comment reinitialized successfully.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								message: "Comment reinitialized successfully."
							}
						},
					},
				},
			},
			400: {
				description: "Bad request",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "[API or/and database error]"
							}
						},
					},
				},
			},
		},
	},
}
