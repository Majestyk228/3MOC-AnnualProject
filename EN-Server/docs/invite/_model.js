module.exports = {
	invite: {
		type: "object",
		properties: {
			code: {
				type: "int",
				description: "Code of invitation",
				example: "1",
			},
			idCommunity: {
				type: "int",
				description: "ID of the community",
				example: "1",
			},
			creationDate: {
				type: "date",
				description: "Creation date of the invitation code",
				example: "2022-05-18"
			},
			endDate: {
				type: "date",
				description: "Expiration date of invitation code",
				example: "2022-05-25"
			}
		},
	},
	invites: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/invite" },
	},
}
