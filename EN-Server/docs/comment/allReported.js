module.exports = {
	get: {
		tags: ["comment"],
		description: "Get the list of all reported comments from a given community.",
		operationId: "allReported",
		parameters: [
			{
				in: "path",
				name: "ididCommunityPost",
				required: true,
				description: "ID of the community",
				schema: {
					type: "integer",
					example: {
						idCommunity: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get all comment from a community.",
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

