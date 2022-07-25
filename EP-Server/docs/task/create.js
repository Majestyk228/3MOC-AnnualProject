module.exports = {
	post: {
		tags: ["task"],
		description: "Creates a new task insite a user's list.",
		operationId: "createTask",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							titre: "ceci est un titre",
							description: "ceci est une description",
							idTask: 5,
							idUser: 1,
							idList: 12,
							idTag: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Task created successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Task created successfully."
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
