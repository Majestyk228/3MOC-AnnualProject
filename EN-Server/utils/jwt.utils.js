//imports
var jwt = require('jsonwebtoken');

//signature du token
const JWT_SIGN_SECRET = 'A9zU5Pvy46y8k3bm74dM7Tgw26FMv2HAgWT';

//exported functions
module.exports = {
	generateTokenForUser: function (userData) {
		return jwt.sign({
			idUser: userData
		},
			JWT_SIGN_SECRET,
			{
				expiresIn: '2h'
			})
	},
	parseAuthorization: function (authorization) {
		return (authorization != null) ? authorization.replace('Bearer ', '') : null;
	},
	getIdUser: function (authorization) {
		var idUser = -1;
		var token = module.exports.parseAuthorization(authorization);
		if (token != null) {
			try {
				var jwtToken = jwt.verify(token, JWT_SIGN_SECRET);
				if (jwtToken != null)
					idUser = jwtToken.idUser;
			} catch (err) { }
		}
		return idUser;
	}
}
