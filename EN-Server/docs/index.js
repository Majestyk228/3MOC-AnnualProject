const invite = require('./invite/index.js')
const admin = require('./admin/index.js')
const post = require('./post/index.js')
const reward = require('./reward/index.js')
const community = require('./community/index.js')

module.exports = {
	openapi: "3.0.1",
	info: {
		version: "1.0.0",
		title: "Exprimons-Nous API Service",
		description: "Service d'API développée pour le fonctionnement de l'ensemble des applications de la plateforme Exprimons-Nous.",
		contact: {
			name: "Sarah KOUTA-LOPATEY",
			email: "sklopatey@myges.fr",
			url: "https://esgi.fr", //change url ASAP
		},
	},
	servers: [
		{
			url: "https://www.titan-photography.com",
			description: "Hosted server",
		},
	],
	paths: {
		...invite.paths,
		...admin.paths,
		...post.paths,
		...reward.paths,
		...community.paths,
	},
	components: {
		schemas: {
			...invite.schema,
			...admin.schema,
			...post.schema,
			...reward.schema,
			...community.schema,
		},
	},
}
