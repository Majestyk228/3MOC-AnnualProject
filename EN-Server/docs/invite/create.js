module.exports = {
	post: {
		tags: ["invite"],
		description: "Create invite",
		operationId: "createInvite",
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
						$ref: "#/components/schemas/invite",
					},
				},
			},
		},
		responses: {
			200: {
				description: "Invitation created successfully",
				content: {
					"application/json": {
						schema: {
							type: "string",
							example: {
								message: "Invitation created successfully",
								code: 21
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
								ERROR: "[API or/and database error]"
							}
						},
					},
				},
			},
		},
	},
}
