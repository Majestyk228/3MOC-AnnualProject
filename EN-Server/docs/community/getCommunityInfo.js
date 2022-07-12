module.exports = {
	get: {
		tags: ["community"],
		description: "Get informations about a community",
		operationId: "getCommunityInfo",
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
				description: "Get community infos",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								idCommunity: 1,
								label: "Val-de-Marne"
							}
						},
					},
				},
			},
			400: {
				description: "Bad Request",
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
