module.exports = {
	get: {
		tags: ["post"],
		description: "Get all posts for a given community",
		operationId: "postByCommunity",
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
				description: "Get all posts for a given community",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/posts",
						},
					},
				},
			},
		},
	},
}
