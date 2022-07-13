//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Inserts in database vote options for a given vote.",
		operationId: "insertVoteOptions",
		requestBody:
		{
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: [
							{
								label: "Oui !",
								idVote: 1
							},
							{
								label: "Non !",
								idVote: 1
							},
							{
								label: "Sans opinion",
								idVote: 1
							}
						]
					}
				},
			},
		},

		responses: {
			200: {
				description: "Get all information on a given vote.",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Vote Options created successfully"
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							ERROR: "[API or/and database error]"
						}
					},
				},
			},
		},
	},
}
