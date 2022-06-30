module.exports = {
	get: {
		tags: ["post"],
		description: "Get the list of reported post from a given community.",
		operationId: "getAllReportedPosts",
		parameters: [
			{
				in: "path",
				name: "idCommunity",
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
				description: "Get the list of reported post from a given community",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/posts",
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
