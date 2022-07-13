//voteInfo
module.exports = {
	post: {
		tags: ["vote"],
		description: "Get all information on a given vote.",
		operationId: "getInfo",
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
				description: "Get all information on a given vote.",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/vote",
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
