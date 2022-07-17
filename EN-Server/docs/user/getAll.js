module.exports = {
	get: {
		tags: ["user"],
		description: "Get all users from a community.",
		operationId: "getAllUsers",
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
			200: {
				description: "Get all users from a community.",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/user",
						},
					},
				},
			},
		},
	},
}
