//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Get all informations about a given vote.",
		operationId: "getVoteInfos",
		requestBody:
		{
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idVote: 1
						}
					}
				},
			},
		},

		responses: {
			200: {
				description: "Get all informations about a given vote.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								exemple: [
									{
										label: "Oui !",
										idVoteOptions: 1
									},
									{
										label: "Non !",
										idVoteOptions: 2
									},
									{
										label: "Sans opinion",
										idVoteOptions: 3
									}
								]
							}
						},
					},
				},
			},
		},
	},
}
