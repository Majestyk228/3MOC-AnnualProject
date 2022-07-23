const getAllRewards = require("./getAllRewards.js")
const getNbRewardByPost = require('./getNbRewardByPost.js')
const useReward = require('./useReward.js')
const schema = require("./_model.js")
module.exports = {
    paths: {
        "/rewards/all": {
            ...getAllRewards,
        },
        "/rewards/nbReward/{idPost}": {
            ...getNbRewardByPost,
        },
        "/useReward": {
            ...useReward
        }
    },
    schema: {
        ...schema,
    },
};
