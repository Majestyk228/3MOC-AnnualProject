module.exports = {
    reward: {
        type: "object",
        properties: {
            idRewards: {
                type: "int",
                description: "ID of reward",
                example: "1",
            },
            title: {
                type: "String",
                description: "Title of reward",
                example: "Super !!!",
            },
            description: {
                type: "String",
                description: "Description of the reward",
                example: "Reward Super !"
            },
            score: {
                type: "int",
                description: "Number of points added or removed when received on a post",
                example: "3"
            },
            cost: {
                type: "int",
                description: "Number of points it costs a user to use this reward",
                example: "6"
            },
            uses: {
                type: "int",
                description: "Number of uses of the reward",
                example: "2"
            }
        },
    },
    rewards: {
        type: "object",
        additionalProperties: { $ref: "#/components/schemas/reward" },
    },
}


/*

"idRewards": 1,
"title": "Super !!!",
"urlMedia": "assets/SuperReward.png",
"description": "Reward Super !",
"score": 5,
"cost": 6,
"uses": 0

*/