module.exports = {
	get: {
		tags: ["comment"],
		description: "Updates the number of reports for a given comment.",
		operationId: "reportComment",
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
				description: "Comment reported successfully.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								message: "Comment reported successfully."
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
