
var admin = require("firebase-admin");

var serviceAccount = require("config/exprimons-nous-firebase-adminsdk-tinzt-bba1677dec.json");

admin.initializeApp({
	credential: admin.credential.cert(serviceAccount)
});

module.exports = { admin }
