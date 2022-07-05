module.exports = {
	delete: {
		tags: ["comment"],
		description: "Delete a comment.",
		operationId: "deleteComment",
		parameters: [
			{
				in: "path",
				name: "idComment",
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
				description: "Comment deleted successfully.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Comment deleted successfully."
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
								ERROR: "Bad Request"
							}
						},
					},
				},
			},
		},
	},
}
