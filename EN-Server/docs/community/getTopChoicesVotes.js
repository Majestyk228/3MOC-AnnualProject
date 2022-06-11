module.exports = {
	get: {
		tags: ["community"],
		description: "Get every top choice on every vote for a specific community",
		operationId: "getTopChoicesVotes",
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
				idVote: 1,
				title: "Premier vote !",
				bestChoice: 1,
				body: "Thanos est-il gentil ou méchant ?"
			},
			400: {
				ERROR: "Bad Request"
			},
		},
	},
}
