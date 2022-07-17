var express  = require('express');
var router   = express.Router();
var User     = require('../models/User');
var util     = require('../util');

// 1. 매칭 신청
router.put('/:username/apply', util.isLoggedin,
 function(req,res,next){
  // 신청자 reqapply에 피신청자 추가
  User.findOne({username:req.params.username})
  .exec(function(err,user){
    if(err||!user) return res.json(util.successFalse(err));
    user.reqapply.push(req.body.adduser)
    user.save(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      else {
        user.password = undefined;
        //res.json(util.successTrue(user));
      }
    });
  })

  // 피신청자 resapply에 신청자 추가
  User.findOne({username:req.body.adduser})
  .exec(function(err,user){
    if(err||!user) return res.json(util.successFalse(err));
    user.resapply.push(req.params.username)
    user.save(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      else {
        user.password = undefined;
        //res.json(util.successTrue(user));
      }
   })
  })
 }
)

// 2. 매칭 수락


module.exports = router;