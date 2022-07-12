module.exports = {
	get: {
		tags: ["vote"],
		description: "Gives the last 5 votes created in a given community",
		operationId: "lastPosts",
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
			201: {
				description: "Gives the last 5 votes created in a given community",
				content: {
					"application/json": {
						type: "object",
						example: [
							{
								idVote: 5,
								title: "Quatrième vote"
							},
							{
								idVote: 4,
								title: "Troisième vote"
							},
							{
								idVote: 3,
								title: "Test 1"
							},
							{
								idVote: 2,
								title: "Test 1"
							},
							{
								idVote: 1,
								title: "Test 1"
							}
						]
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
			}
		},
	},
}
