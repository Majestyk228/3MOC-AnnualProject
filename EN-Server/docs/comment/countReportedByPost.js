module.exports = {
	get: {
		tags: ["comment"],
		description: "Get the number of reported comment from a given post.",
		operationId: "countReportedByPost",
		parameters: [
			{
				in: "path",
				name: "idPost",
				required: true,
				description: "ID of the post",
				schema: {
					type: "integer",
					example: {
						idPost: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get the number of reported comment from a given post.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbComment: 9
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
