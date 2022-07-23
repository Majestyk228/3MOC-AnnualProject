// useReward.js
module.exports = {
	post: {
		tags: ["rewards"],
		description: "Submits the reward submmition to a post.",
		operationId: "useReward",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idRewards: 1,
							idPost: 2,
							idUser: 8
						}
					}
				},
			},
		},
		responses: {
			201: {
				description: "Reward added successfully.",
				content: {
					"application/json": {
						type: "object",
						example: {
							message: "Reward added successfully."
						}
					},
				},
			},
			400: {
				description: "Missing infos",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "Missing info(s)"
						}
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "[API or/and database error]"
						}
					},
				},
			},
		},
	},
}
