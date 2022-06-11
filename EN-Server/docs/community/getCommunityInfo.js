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
		requestBody: {
			content: {
				"application/json": {
					schema: {
						$ref: "#/components/schemas/community",
					},
				},
			},
		},
		responses: {
			200: {
				idCommunity: 1,
				label: "Val-de-Marne"
			},
			400: {
				ERROR: "Bad Request"
			},
		},
	},
}
