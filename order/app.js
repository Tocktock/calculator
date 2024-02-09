const express = require('express');
const axios = require('axios');
const app = express();
const port = 3000;
app.use(express.json());

// POST endpoint
app.post('/create', async (req, res) => {
    const resultMessage = ["order server request received"];

    try {
        const response = await axios.get("http://price-service/calculate").then();
        resultMessage.push("request for price server is successful");
    } catch (error) {
        resultMessage.push("request for price server failed");
    }

    res.json(resultMessage);
});

// Start the server
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});
