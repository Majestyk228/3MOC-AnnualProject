module.exports = {
	post: {
		tags: ["community"],
		description: "Get stats from a community",
		operationId: "getCommunityStats",
		parameters: [],
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "integer",
						example: {
							idCommunity: 2
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Get community stats",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbUsers: 10,
								totalPointsCommunity: 500,
								nbPost: 10,
								nbVote: 10
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
