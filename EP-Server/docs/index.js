const comment = require('./comment/index.js')
const list = require('./list/index.js')
//const tag = require('./tag/index.js')
const task = require('./task/index.js')
//const user = require('./user/index.js')

module.exports = {
    openapi: "3.0.1",
    info: {
        version: "2.2.8",
        title: "Exprimons-Nous Projet API Service",
        description: "Service d'API développée pour le fonctionnement du client lourd Exprimons Projet.",
        contact: {
            name: "Sarah KOUTA-LOPATEY",
            email: "skoutalopatey@myges.fr",
            url: "https://rr.noordstar.me/sarah-kouta-lopateys-website-9e15903b", //change url ASAP
        },
    },
    servers: [
        {
            url: "https://www.java.titan-photography.com",
            description: "Hosted server",
        },
    ],
    paths: {
        ...comment.paths,
        ...list.paths,
        //...tag.paths,
        ...task.paths,
        //...user.paths,
    },
    components: {
        schemas: {
            ...comment.schema,
            ...list.schema,
            //...tag.schema,
            ...task.schema,
            //...user.schema
        },
    },
}