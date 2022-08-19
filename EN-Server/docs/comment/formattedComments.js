module.exports = {
	get: {
		tags: ["comment"],
		description: "Get all comment from a post. [formatted for Android client]",
		operationId: "commentByPostFormatted",
		parameters: [
			{
				in: "header",
				name: "token",
				required: true,
				description: "User token",
				schema: {
					type: "string",
					example: {
						token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZFVzZXIiOjYsImlhdCI6MTY2MDc2Mjg0NSwiZXhwIjoxNjYxMzY3NjQ1fQ.mORMwfV6A6KmACBTwvMKAcPCMtp0ks93A"
					}
				}
			},
			{
				in: "path",
				name: "idPost",
				required: true,
				description: "ID of the post",
				schema: {
					type: "integer",
					example: {
						idPost: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get all comment from a post.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: [
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40354eme comm du val de marne haha",
									idComment: 1,
									anonymous: "True"
								},
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40355eme comm du val de marne haha",
									idComment: 3,
									anonymous: "True"
								},
								{
									firstName: "Black",
									lastName: "Sherif",
									body: "40356eme comm du val de marne haha",
									idComment: 8,
									anonymous: "True"
								}
							]
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
			},
		},
	},
}
