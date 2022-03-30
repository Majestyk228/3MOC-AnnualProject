'use strict';
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('Posts', {
      idPost: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      idUser: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
          model: 'Users',
          key: 'idUser'
        }
      },
      idAdmin: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
          model: 'Admins',
          key: 'idAdmin'
        }
      },
      idCommunity: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
          model: 'Communities',
          key: 'idCommunity'
        }
      },
      title: {
        allowNull: false,
        type: Sequelize.STRING
      },
      body: {
        allowNull: false,
        type: Sequelize.STRING
      },
      date: {
        allowNull: false,
        type: Sequelize.DATEONLY
      },
      time: {
        allowNull: false,
        type: Sequelize.TIME
      },
      likes: {
        allowNull: false,
        type: Sequelize.INTEGER
      },
      dislikes: {
        allowNull: false,
        type: Sequelize.INTEGER
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('Posts');
  }
};