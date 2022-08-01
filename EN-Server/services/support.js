// IMPORTATION OF NodeMailer
const nodemailer = require("nodemailer");
const user = require("../services/user.js");
const community = require('../services/community.js')

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

async function sendMail2(body) {

	// RETREIVING DATA FROM DB TO DISPLAY THEM IN THE MAIL
	const userInfos = await user.getUserInfo(body.idUser);
	const userCommunity = await community.getCommunityInfo(body.idCommunity);

	let transporter = nodemailer.createTransport({
		host: "mail.titan-photography.com",
		port: 465,
		secure: true,
		auth: {
			user: 'en.server@titan-photography.com',
			pass: 'vx$3]6PRXj*4'
		},
		tls: {
			rejectUnauthorized: false,
		},
	});

	// TEMPLATE OF THE MAIL
	let htmlText = '<div style="background-color: #3e92c6; height: 80px; padding-top: 32px; padding-bottom: 32px;"> <h1 style="color: #ffffff; text-align: center; font-family:arial;">Message de ' + userInfos[0].firstName + ' ' + userInfos[0].lastName + ' ! - ' + userCommunity[0].label + ' </h1></div><h2 style="color: #245979;font-family:arial;">Sujet du message : <span style="text-decoration: underline;">' + body.title + '</span></h2><h2 style="color: #245979;font-family:arial;">Corps du message :&nbsp;</h2><p style="padding-left: 40px;font-family:arial;"><em>' + body.body + '</em></p><div style="background-color: #3e92c6; height: 150px; padding-top: 35px;"><p style="font-family: arial; text-align: center; color: #ffffff;"><em>This mail was sent via the <strong>Exprimons-Nous API Service</strong> using the NodeMailer NPM module.</em></p><p style="font-family: arial; text-align: center; color: #ffffff;"><em>Exprimons-Nous API Service Documentation <a style="color: #ffffff;" title="Exprimons-Nous API Service - Documentation" href="https://www.titan-photography.com/swagger" target="_blank">here</a>.</em></p><p style="color: #ffffff;font-family: arial; text-align: center;"><em>NodeMailer official website <a style="color: #ffffff;" title="NodeMailer - Official Website" href="https://nodemailer.com/about/" target="_blank">here</a>.</em></p></div>';

	// MAIL SENT BY API TO ASSISTANCE EMAIL ADDRESS
	let mailDetails = {
		from: 'en.server@titan-photography.com',
		to: 'exprimonsnous.assistance@gmail.com',
		subject: 'Envoyé depuis Exprimons-Nous (Android)',
		html: htmlText
	};

	// SENDING MAIL
	transporter.sendMail(mailDetails, function (err, data) {
		if (err) {
			console.log('Error Occurs : ' + err.message);
		} else {
			console.log('Email sent successfully');
		}
	});
}

module.exports = {
	sendMail,
	sendMail2
}
