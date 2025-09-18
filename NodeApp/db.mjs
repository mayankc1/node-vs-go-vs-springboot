import { DataTypes, Sequelize } from "sequelize";

const dbUser = process.env.dbUser;
const dbUserPass = process.env.dbUserPass;
const dbName = process.env.dbName;

const sequelize = new Sequelize(
  `postgres://${dbUser}:${dbUserPass}@localhost:5432/${dbName}`,
  {
    logging: false,
    pool: {
      max: 10,
      min: 10,
    },
  },
);
await sequelize.authenticate();

const ShortenedUrl = sequelize.define("shortenedurl", {
  id: {
    type: DataTypes.STRING,
    primaryKey: true,
  },
  srcurl: DataTypes.STRING,
  created: DataTypes.DATE,
  lastaccessed: DataTypes.DATE,
}, {
  timestamps: false,
});

export async function save(id, srcUrl) {
  await ShortenedUrl.create({
    id,
    srcurl: srcUrl,
    created: new Date(),
    lastaccessed: new Date(),
  });
  return true;
}
