module.exports = {
	delete: {
		tags: ["comment"],
		description: "Deletes a comment for a user's task.",
		operationId: "deleteComment",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idComment",
				schema: {
					type: "integer",
					example: {
						idComment: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Comment deleted successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Comment deleted successfully."
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
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
