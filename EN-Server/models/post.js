'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Post extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      models.Post.belongsTo(models.Community, {
        foreignKey: {
          allowNull: false
        }
      });
      models.Post.belongsTo(models.User, {
        foreignKey: {
          allowNull: false
        }
      });
      models.Post.belongsTo(models.Admin, {
        foreignKey: {
          allowNull: false
        }
      });
    }
  }
  Post.init({
    title: DataTypes.STRING,
    body: DataTypes.STRING,
    date: DataTypes.DATEONLY,
    time: DataTypes.TIME,
    likes: DataTypes.INTEGER,
    dislikes: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Post',
  });
  return Post;
};