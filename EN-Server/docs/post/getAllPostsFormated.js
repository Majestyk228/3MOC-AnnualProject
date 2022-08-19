module.exports = {
	post: {
		tags: ["post"],
		description: "Get the list of post from a given community, formatted to be used for Android client Exprimons-nous.",
		operationId: "getAllPostsFormatted",
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
				name: "idCommunity",
				required: true,
				description: "ID of the community",
				schema: {
					type: "integer",
					example: {
						idCommunity: 1
					}
				}
			},
		],
		responses: {
			200: {
				description: "Get the list of post from a given community, formatted to be used for Android client Exprimons-nous. NOTE : comments and rewards initialized at 0 ; can be updated with another route.",
				content: {
					"application/json": {
						schema: {
							type: "object",
							example: {
								idPost: 1,
								firstName: "Fally",
								lastName: "IPUPA",
								title: "Premier poste !",
								body: "Ceci est le premier poste pour la commune du Val-de-Marne !!!",
								likes: 0,
								dislikes: 0,
								comments: 0,
								rewards: 0
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
