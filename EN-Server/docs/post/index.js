const getAllPost = require("./getAll.js")
const getOnePost = require("./get.js")
const getAllPostsFormated = require("./getAllPostsFormated.js")
const nbReportedPosts = require("./nbReportedPosts.js")
const createPost = require("./createPost.js")
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
        }
    },
    schema: {
        ...schema,
    },
};
