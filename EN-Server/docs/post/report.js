module.exports = {
	get: {
		tags: ["post"],
		description: "Updates the number of reports for a given post.",
		operationId: "reportPost",
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
				description: "Post reported successfully.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								message: "Post reported successfully."
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
