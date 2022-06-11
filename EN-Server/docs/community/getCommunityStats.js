module.exports = {
	get: {
		tags: ["community"],
		description: "Get stats from a community",
		operationId: "getCommunityStats",
		parameters: [
			{
				in: "body",
				required: true,
				type: "int",
				name: "idCommunity",
				schema: {
					type: "integer",
					example: {
						idCommunity: 2
					}
				}
			},
		],
		requestBody: {
			content: {
				"application/json": {
					schema: {
						$ref: "#/components/schemas/community",
					},
				},
			},
		},
		responses: {
			200: {
				nbUsers: 10,
				totalPointsCommunity: 500,
				nbPost: 10,
				nbVote: 10
			},
			400: {
				ERROR: "Bad Request"
			},
		},
	},
}
