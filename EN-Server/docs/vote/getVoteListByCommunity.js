module.exports = {
	get: {
		tags: ["vote"],
		description: "Get all votes in a given community",
		operationId: "getVoteListByCommunity",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idCommunity",
				schema: {
					type: "integer",
					example: {
						idCommunity: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get all votes in a given community",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/votes",
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
			}
		},
	},
}
