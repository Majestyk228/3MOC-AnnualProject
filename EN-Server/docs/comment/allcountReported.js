module.exports = {
	get: {
		tags: ["comment"],
		description: "Get the number of reported comment on the plateform.",
		operationId: "allcountReported",
		parameters: [],
		responses: {
			200: {
				description: "Get the number of reported comment on the plateform.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								nbComment: 9
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
