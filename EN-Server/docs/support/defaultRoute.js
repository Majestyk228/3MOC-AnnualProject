// useReward.js
module.exports = {
	post: {
		tags: ["support"],
		description: "Sends an email to support email address.",
		operationId: "defaultRoute",
		requestBody: {
			content: {
				"application/json": {
					schema: {
						type: "object",
						example: {
							idUser: 1,
							idCommunity: 2,
							title: "Title of the mail",
							body: "Body of the mail"
						}
					}
				},
			},
		},
		responses: {
			200: {
				description: "Message successfully sent to support service.",
				content: {
					"application/json": {
						type: "object",
						example: {
							Message: "Message successfully sent to support service."
						}
					},
				},
			},
			400: {
				description: "Bad Request",
				content: {
					"application/json": {
						type: "object",
						example: {
							Error: "[NodeMailer error]"
						}
					},
				},
			},
		},
	},
}
