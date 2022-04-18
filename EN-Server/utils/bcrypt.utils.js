const bcrypt = require('bcrypt');

function hashPwd(password) {
	bcrypt.hash(password, 5, function (err, hashedPwd) {
		return hashedPwd;
	});
};

module.exports = hashPwd;
