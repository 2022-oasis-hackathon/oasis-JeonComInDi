var express    = require('express');
var app        = express();
var path       = require('path');
var mongoose   = require('mongoose');
var bodyParser = require('body-parser');

// Database
mongoose.Promise = global.Promise;
mongoose.connect(process.env.MONGO_DB_LOGIN_API, {useNewUrlParser:true, useUnifiedTopology:true});
var db = mongoose.connection;
db.once('open', function () {
   console.log('DB connected!');
});
db.on('error', function (err) {
  console.log('DB ERROR:', err);
});

// Middlewares
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(function (req, res, next) {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
  res.header('Access-Control-Allow-Headers', 'content-type, x-access-token');
  next();
});

// API
app.use('/api/users', require('./api/users'));
app.use('/api/auth', require('./api/auth'));
app.use('/api/category', require('./api/category'));
app.use('/api/matching', require('./api/matching'));
// Server
var port = 3001;
app.listen(port, function(){
  console.log('listening on port:' + port);
});
