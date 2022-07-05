module.exports = {
	get: {
		tags: ["comment"],
		description: "Get the number of comment from a post.",
		operationId: "countByPost",
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
				description: "Get the count of comment throughout the plateform.",
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
								ERROR: "Bad Request"
							}
						},
					},
				},
			},
		},
	},
}
