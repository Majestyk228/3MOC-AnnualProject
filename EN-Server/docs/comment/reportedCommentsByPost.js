module.exports = {
	get: {
		tags: ["comment"],
		description: "Get all reported comment from a post.",
		operationId: "reportedCommentByPost",
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
				description: "Get all reported comment from a post.",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/comments",
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
