// IMPORTATION OF NodeMailer
const nodemailer = require("nodemailer");

async function sendMail(body) {
	/*const rows = await db.query("SELECT com.* FROM Comment com, Post p, User u WHERE com.idPost = p.idPost AND u.idUser = com.idUser AND reports>0 AND com.idPost = " + idPost + ";", "");
	return rows;*/

	// TEST USER
	let testAccount = await nodemailer.createTestAccount();

	// create reusable transporter object using the default SMTP transport
	let transporter = nodemailer.createTransport({
		host: "smtp.ethereal.email",
		port: 587,
		secure: false, // true for 465, false for other ports
		auth: {
			user: testAccount.user, // generated ethereal user
			pass: testAccount.pass, // generated ethereal password
		},
	});

	// send mail with defined transport object
	let info = await transporter.sendMail({
		from: '"Fred Foo 👻" <foo@example.com>', // sender address
		to: "exprimonsnous.assistance@gmail.com", // list of receivers
		subject: "Hello ✔", // Subject line
		text: "Hello world? This i s mail sent by API", // plain text body
		html: "<b>Hello world? This i s mail sent by API</b>", // html body
	});
}

module.exports = {
	sendMail
}
