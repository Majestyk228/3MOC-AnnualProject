module.exports = {
	get: {
		tags: ["invite"],
		description: "Get all invite by community",
		operationId: "getAllInvitesByCommunity",
		parameters: [
			{
				in: "path",
				name: "idCommunity",
				required: true,
				description: "ID of the vote",
				schema: {
					type: "integer",
					example: {
						idCommunity: 7
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get all invite by community",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/invites",
						},
					},
				},
			},
		},
	},
}
