module.exports = {
	put: {
		tags: ["task"],
		description: "Update a given task.",
		operationId: "updateTask",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idTask: 1,
							title: "Connexion user à l'application Android",
							description: "Faire la requete vers l'API afin d'assurer la connexion de l'utilisateur de l'application Android",
							idList: 1,
							idTag: 1
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Task updated successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Task updated successfully"
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
			}
		},
	},
}
