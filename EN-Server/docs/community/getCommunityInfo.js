module.exports = {
	get: {
		tags: ["community"],
		description: "Get informations about a community",
		operationId: "getCommunityInfo",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "Admin token",
				schema: {
					type: "string",
					example: {
						token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY2MDc2Mjg0NSwiZXhwIjoxNjYxMzY3NjQ1fQ.mORMwfV6A6KmACBTwvMKAcPCMtp0ks93A"
					}
				}
			},
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
			404: {
				description: "Missing token in header",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Missing token in header"
							}
						},
					},
				},
			},
			406: {
				description: "Token expired/incorrect",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								ERROR: "Token expired/incorrect"
							}
						},
					},
				},
			}
		},
	},
}
