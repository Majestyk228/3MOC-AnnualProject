module.exports = {
	get: {
		tags: ["post"],
		description: "Get the number of reported posts for a given community.",
		operationId: "nbReportedPosts",
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
				description: "Get the number of reported posts for a given community.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbReportedPosts: 0
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
