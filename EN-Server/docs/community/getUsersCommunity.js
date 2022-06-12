module.exports = {
	get: {
		tags: ["community"],
		description: "Get communities of a specific user",
		operationId: "getUsersCommunity",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idUser",
				schema: {
					type: "integer",
					example: {
						idUser: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get communities of a specific user",
				content: {
					"application/json": {
						schema: {
							type: "integer",
							example: {
								idcommunity: 1
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
							type: "string",
							example: {
								ERROR: "Bad Request"
							}
						},
					},
				},
			},
		},
	},
}
