const express = require('express');
const app = express();
const port = 3000;
const axios = require('axios');
// Middleware to parse JSON bodies
app.use(express.json());

// POST endpoint
app.get('/calculate', async (req, res) => {
    console.log("price server request received");
    res.json("price server request received");
});

// Start the server
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});
