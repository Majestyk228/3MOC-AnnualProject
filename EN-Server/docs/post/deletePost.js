module.exports = {
	delete: {
		tags: ["post"],
		description: "Delete a post from the database.",
		operationId: "deletePost",
		parameters: [
			{
				in: "path",
				name: "idPost",
				required: true,
				description: "ID of the post",
				schema: {
					type: "integer",
					example: {
						idPost: 7
					}
				}
			},
		],
		responses: {
			200: {
				description: "Post deleted successfully.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Post deleted successfully"
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
							type: "string",
							example: {
								ERROR: "Bad request"
							}
						},
					},
				},
			},
		},
	},
}
