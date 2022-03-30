'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Vote extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      models.Vote.belongsTo(models.User, {
        foreignKey: {
          allowNull: false
        }
      });
      models.Vote.belongsTo(models.Admin, {
        foreignKey: {
          allowNull: false
        }
      });
    }
  }
  Vote.init({
    title: DataTypes.STRING,
    body: DataTypes.STRING,
    nbChoice: DataTypes.INTEGER,
    important: DataTypes.BOOLEAN
  }, {
    sequelize,
    modelName: 'Vote',
  });
  return Vote;
};