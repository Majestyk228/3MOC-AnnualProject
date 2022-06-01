const getAllRewards = require("./getAllRewards.js")
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/rewards/all": {
            ...getAllRewards,
        },
    },
    schema: {
        ...schema,
    },
};
