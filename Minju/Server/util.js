var jwt = require('jsonwebtoken');

var util = {};

util.successTrue = function(data){
  return {
    success:true,
    message:null,
    errors:null,
    data:data
  };
};

util.successFalse = function(err, message){
  if(!err&&!message) message = 'data not found';
  console.log(err)
  return {
    success:false,
    message:message,
    errors:(err)? util.parseError(err): null,
    data:null
  };
};

util.applyFalse = function(data){
  return {
    success:false,
    message:null,
    errors:null,
    data:data
  }
}

util.parseError = function(errors){
  var parsed = {};
  if(errors.name == 'ValidationError'){
    for(var name in errors.errors){
      var validationError = errors.errors[name];
      parsed[name] = { message:validationError.message };
    }
  } else if(errors.code == '11000' && errors.errmsg.indexOf('username') > 0) {
    parsed.username = { message:'해당 아이디가 이미 존재합니다!' };
  } else {
    parsed.unhandled = errors;
  }
  return parsed;
};


// middlewares
util.isLoggedin = function(req,res,next){
  var token = req.headers['x-access-token'];
  if (!token) return res.json(util.successFalse(null,'토큰이 필요합니다!'));
  else {
    jwt.verify(token, process.env.JWT_SECRET, function(err, decoded) {
      if(err) return res.json(util.successFalse(err));
      else{
        req.decoded = decoded;
        next();
      }
    });
  }
};

module.exports = util;
