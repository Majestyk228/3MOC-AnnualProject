//getNbRewardByPost.js
module.exports = {
	get: {
		tags: ["rewards"],
		description: "Get the number of rewards from a given post.",
		operationId: "getNbRewardByPost",
		parameters: [
			{
				in: "path",
				name: "idPost",
				required: true,
				description: "ID of the post",
				schema: {
					type: "integer",
					example: {
						idPost: 1
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
							type: "object",
							example: {
								rewards: 2,
							}
						},
					},
				},
			},
		},
	},
}
