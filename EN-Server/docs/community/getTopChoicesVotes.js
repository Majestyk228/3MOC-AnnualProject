module.exports = {
	post: {
		tags: ["community"],
		description: "Get every top choice on every vote for a specific community",
		operationId: "getTopChoicesVotes",
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
				description: "Gives for every vote its ID, title, body and the choice mosty selected by users.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								idVote: 1,
								title: "Premier vote !",
								bestChoice: 1,
								body: "Thanos est-il gentil ou méchant ?"
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						schema: {
							type: "string",
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
