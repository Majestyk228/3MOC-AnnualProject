const getAllPost = require("./getAll.js")
const getOnePost = require("./get.js")
const getAllPostsFormated = require("./getAllPostsFormated.js")
const nbReportedPosts = require("./nbReportedPosts.js")
const createPost = require("./createPost.js")
const lastPosted = require("./lastPosted.js")
const getAllReportedPosts = require('./getAllReportedPosts.js')
const postsByCommunity = require('./postsByCommunity.js')
const updatePost = require('./updatePost.js');
const deletePost = require('./deletePost.js');
const likePost = require('./likePost.js');
const dislikePost = require('./dislikePost.js');
const report = require('./report.js');
const reportReinit = require('./reportReinit.js');
const getFormattedPost = require('./getFormattedPost.js');
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/post/all": {
            ...getAllPost,
        },
        "/post/{idPost}": {
            ...getOnePost,
        },
        "/post/formatted/{idPost}": {
            ...getAllPostsFormated,
        },
        "/post/nbReportedPosts/{idCommunity}": {
            ...nbReportedPosts,
        },
        "/post/create": {
            ...createPost,
        },
        "/post/lastPosted/{idCommunity}": {
            ...lastPosted,
        },
        "/post/reportedPosts/{idCommunity}": {
            ...getAllReportedPosts,
        },
        "/post/all/{idCommunity}": {
            ...postsByCommunity,
        },
        "/post/updatePost": {
            ...updatePost,
        },
        "/post/delete/{idPost}": {
            ...deletePost,
        },
        "/post/like": {
            ...likePost,
        },
        "/post/dislike": {
            ...dislikePost,
        },
        "/post/report/{idPost}": {
            ...report,
        },
        "/post/reportReinit/{idPost}": {
            ...reportReinit,
        },
        "/post/formattedPost/{idPost}": {
            ...getFormattedPost,
        }
    },
    schema: {
        ...schema,
    },
};
