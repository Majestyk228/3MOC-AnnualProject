module.exports = {
	delete: {
		tags: ["list"],
		description: "Deletes a list for a user.",
		operationId: "deleteList",
		parameters: [
			{
				in: "path",
				required: true,
				type: "int",
				name: "idList",
				schema: {
					type: "integer",
					example: {
						idList: 2
					}
				}
			},
		],
		responses: {
			200: {
				description: "List deleted successfully.",
				content: {
					"application/json": {
						schema: {
							example: {
								Message: "List deleted successfully."
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
