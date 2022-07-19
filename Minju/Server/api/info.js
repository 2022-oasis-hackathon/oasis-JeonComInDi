var express  = require('express');
var router   = express.Router();
var User     = require('../models/User');
var util     = require('../util');
const PROMISE = require("bluebird");

// 1. 내가 신청한 목록 출력
router.get('/my_request', util.isLoggedin,
  function(req,res,next){
    User.findById(req.decoded._id)
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      res.json(util.successTrue(user.reqapply));
    })
  } 
)

// 2. 내가 신청받은 목록 출력
router.get('/my_response', util.isLoggedin,
  function(req,res,next){
    User.findById(req.decoded._id)
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      res.json(util.successTrue(user.resapply));
    })
  } 
)

// 3. 매칭 목록 출력
router.get('/my_matching', util.isLoggedin,
  function(req,res,next){
    User.findById(req.decoded._id)
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      res.json(util.successTrue(user.matchuser));
    })
  } 
)

  module.exports = router;

  // TODO : 반복문 동기화 처리