module.exports = (sequelize, DataTypes) => {
    return sequelize.define('Student', {
      address: DataTypes.STRING,
      birthday: DataTypes.STRING,
      campus: DataTypes.STRING,
      elementary_school: DataTypes.STRING,
      id_number: DataTypes.STRING,
      lrn: DataTypes.STRING,
      name: DataTypes.STRING,
      place_of_birth: DataTypes.STRING,
      via: DataTypes.STRING,
      family_saint: DataTypes.STRING,
      batch_number: DataTypes.STRING,
      image_url: DataTypes.STRING,
    });
  };
  