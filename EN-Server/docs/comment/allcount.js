module.exports = {
	get: {
		tags: ["comment"],
		description: "Get the count of comment throughout the plateform.",
		operationId: "allcount",
		parameters: [],
		responses: {
			200: {
				description: "Get the count of comment throughout the plateform.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbComment: 7
							}
						},
					},
				},
			},
			400: {
				description: "Bad request",
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
