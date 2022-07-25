module.exports = {
	delete: {
		tags: ["task"],
		description: "Deletes a task from a user's list.",
		operationId: "deleteTask",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idTask",
				schema: {
					type: "integer",
					example: {
						idTask: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Task deleted successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "Task deleted successfully."
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
