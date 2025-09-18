import cluster from "node:cluster";
import express from "express";
import { handleRequest } from "./expressController.mjs";

const numClusterWorkers = parseInt(process.argv[2] || 1);

if (cluster.isPrimary) {
  for (let i = 0; i < numClusterWorkers; i++) {
    cluster.fork();
  }

  cluster.on(
    "exit",
    (worker, code, signal) => console.log(`worker ${worker.process.pid} died`),
  );
} else {
  const app = express();
  app.use(express.json());

  app.post("/shorten", handleRequest);

  app.listen(3000);
}
