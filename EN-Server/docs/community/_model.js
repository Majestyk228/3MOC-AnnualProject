module.exports = {
	community: {
		type: "object",
		properties: {
			idCommunity: {
				type: "int",
				description: "ID of community",
				example: "1",
			},
			label: {
				type: "string",
				description: "Label of community",
				example: "Val-de-Marne",
			},
			enterprise: {
				type: "int",
				description: "Defines if the community is an enterprise or not",
				example: "1"
			},
			description: {
				type: "string",
				description: "Description of the community",
				example: "Commune de 1,395 Million d'habitants"
			}
		},
	},
	communities: {
		type: "object",
		additionalProperties: { $ref: "#/components/schemas/community" },
	},
}
