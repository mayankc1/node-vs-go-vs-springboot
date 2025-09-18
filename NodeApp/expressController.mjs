import { shorten } from "./service.mjs";

export async function handleRequest(req, res) {
  if (!(req.body && req.body.srcUrl)) {
    return res.status(400).send({ errMsg: "Parameter 'srcUrl' is missing" });
  }

  const srcUrl = req.body.srcUrl;
  if (srcUrl.length > 300) {
    return res.status(400).send({
      errMsg: "Parameter 'srcUrl' must not be more than 250 characters",
    });
  }
  if (!(srcUrl.startsWith("http://") || srcUrl.startsWith("https://"))) {
    return res.status(400).send({
      errMsg: "Parameter 'srcUrl' must start with http:// or https://",
    });
  }

  const shortenedUrl = await shorten(srcUrl);
  if (!shortenedUrl) {
    return res.status(500).send({ errMsg: "Failed to shorten" });
  }
  res.json({ srcUrl, shortenedUrl });
}
