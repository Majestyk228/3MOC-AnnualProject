const invite = require('./invite/index.js')
const admin = require('./admin/index.js')
const post = require('./post/index.js')
const reward = require('./reward/index.js')
const community = require('./community/index.js')
const vote = require('./vote/index.js')
const user = require('./user/index.js')
const comment = require('./comment/index.js')

module.exports = {
	openapi: "3.0.1",
	info: {
		version: "1.0.0",
		title: "Exprimons-Nous API Service",
		description: "Service d'API développée pour le fonctionnement de l'ensemble des applications de la plateforme Exprimons-Nous.",
		contact: {
			name: "Sarah KOUTA-LOPATEY",
			email: "skoutalopatey@myges.fr",
			url: "https://rr.noordstar.me/sarah-kouta-lopateys-website-9e15903b", //change url ASAP
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
		...vote.paths,
		...user.paths,
		...comment.paths
	},
	components: {
		schemas: {
			...invite.schema,
			...admin.schema,
			...post.schema,
			...reward.schema,
			...community.schema,
			...vote.schema,
			...user.schema,
			...comment.schema
		},
	},
}
