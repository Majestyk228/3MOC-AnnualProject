module.exports = {
	post: {
		tags: ["community"],
		description: "Get the first 3 users with highest score from a given community",
		operationId: "getThreeBestUserByCommunity",
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
				description: "Get the first 3 users with highest score from a given community community stats",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: [
								{
									idUser: 7,
									firstName: "Bo",
									lastName: "BURNHAM"
								},
								{
									idUser: 6,
									firstName: "Fally",
									lastName: "IPUPA"
								},
								{
									idUser: 8,
									firstName: "Master",
									lastName: "KJ"
								}
							]
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
