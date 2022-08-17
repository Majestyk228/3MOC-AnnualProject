/*const config = {
	db: {
		host: "exprimonsnous.coqdgtazlflp.us-east-1.rds.amazonaws.com",
		user: "admin",
		password: "kB9qG7e3zEU3",
		database: "exprimonsNous",
		dateStrings: 'date'
	}
};
module.exports = config;*/


const config = {
	db: {
		host: "exprimonsnous-bkp.c50gqqod3ibz.eu-west-3.rds.amazonaws.com",
		user: "admin",
		password: "kB9qG7e3zEU3",
		database: "exprimonsNous_bkp",
		dateStrings: 'date'
	}
};


const JWT_SIGN_SECRET = 'A9zU5Pvy46y8k3bm74dM7Tgw26FMv2HAgWT';

module.exports = { config, JWT_SIGN_SECRET };
