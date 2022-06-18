const getAllPost = require("./getAll.js")
const getOnePost = require("./get.js")
const getAllPostsFormated = require("./getAllPostsFormated.js")
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
        }
    },
    schema: {
        ...schema,
    },
};
