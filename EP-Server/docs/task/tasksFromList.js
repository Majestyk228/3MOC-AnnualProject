module.exports = {
	get: {
		tags: ["task"],
		description: "Gets the infos of a given task.",
		operationId: "tasksFromList",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idList",
				schema: {
					type: "integer",
					example: {
						isList: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "Gets the list of task stored in a list.",
				content: {
					"application/json": {
						schema: {
							$ref: "#/components/schemas/tasks",
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
