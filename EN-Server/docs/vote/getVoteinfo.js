//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Get title, body, vote options and number of time each option got selected for a given vote (NEEDS TO BE FIXED)",
		operationId: "getVoteInfo",
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
				description: "Get title, body, vote options and number of time each option got selected for a given vote",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								title: "Premier vote !",
								body: "Thanon est-il gentil ou mechant ?",
								voteOptions: [
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
								],
								nbChoiceVoteOptions: [
									{
										nbChoice: 0
									},
									{
										nbChoice: 2
									},
									{
										nbChoice: 0
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
