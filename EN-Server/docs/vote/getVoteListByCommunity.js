module.exports = {
	post: {
		tags: ["vote"],
		description: "Get all votes in a given community",
		operationId: "getVoteListByCommunity",
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
		responses: {
			200: {
				description: "Get all rewards",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								idVote: 1,
								title: "Premier vote"
							}
						},
					},
				},
			},
		},
	},
}
