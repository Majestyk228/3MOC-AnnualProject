'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class VoteOptions extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
    }
  }
  VoteOptions.init({
    label: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'VoteOptions',
  });
  return VoteOptions;
};