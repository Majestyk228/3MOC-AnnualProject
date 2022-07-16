//getCommunity.js
module.exports = {
	get: {
		tags: ["invite"],
		description: "Get idCommunity by a given code",
		operationId: "getCommunity",
		parameters: [
			{
				in: "path",
				name: "code",
				required: true,
				description: "Identification code of invitation",
				schema: {
					type: "integer",
					example: {
						code: 15
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get idCommunity by a given code",
				content: {
					"application/json": {
						type: "object",
						example: {
							idCommunity: 2
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
							ERROR: "[API or/and database error]"
						}
					},
				},
			}
		},
	},
}
