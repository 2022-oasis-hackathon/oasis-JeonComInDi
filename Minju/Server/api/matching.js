var express  = require('express');
var router   = express.Router();
var User     = require('../models/User');
var util     = require('../util');

// 1. 매칭 신청
router.put('/:username/apply', util.isLoggedin,
 function(req,res,next){
  // 신청자(decoded) reqapply에 피신청자 id(param) 추가)
  User.findOne({username:req.decoded.username})
  .exec(function(err,user){
    if(err||!user) return res.json(util.successFalse(err));
    // 피신청자가 신청자의 reqapply와 matchuser 리스트에 존재하지 않으면
    if(!(user.reqapply.includes(req.params.username))&&!(user.matchuser.includes(req.params.username))){
      user.reqapply.push(req.params.username)
      user.save(function(err,user){
        if(err||!user) return res.json(util.successFalse(err));
      });
        // 피신청자 resapply에 신청자 id 추가
        User.findOne({username:req.params.username})
        .exec(function(err,user){
          if(err||!user) return res.json(util.successFalse(err));
          user.resapply.push(req.decoded.username);
          user.save(function(err,user){
            if(err||!user) return res.json(util.successFalse(err));
            else {
              res.json(util.successTrue("요청이 전송되었습니다!"));
            }
          })
        })
    }
    // 이미 신청했으면
    else {
      if((user.reqapply.includes(req.params.username))){
        return res.json(util.applyFalse("매칭 요청을 이미 보냈습니다!"))
      }
      else{
        return res.json(util.applyFalse("이미 매칭이 된 사용자입니다!"))
      }
    }
  })
 }
)

// 2. 매칭 수락
router.put('/:username/accept', util.isLoggedin,
  function(req,res,next){
    // 피신청자 resapply에 신청자 id 제거 후 matchuser로 이동
    User.findOne({username:req.decoded.username})
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      user.matchuser.push(req.params.username);
      for(var i = 0; i < user.resapply.length; i++){
        if (user.resapply[i] === req.params.username){
          user.resapply.splice(i, 1);
          break;
        }
      }
      user.save(function(err,user){
        if(err||!user) return res.json(util.successFalse(err));
      })
    })
    
    // 신청자 reqapply에 피신청자 id 제거 후 matchuser로 이동
    User.findOne({username:req.params.username})
    .exec(function(err,user1){
      if(err||!user1) return res.json(util.successFalse(err));
      user1.matchuser.push(req.decoded.username);
      for(var i = 0; i < user1.reqapply.length; i++){
        if (user1.reqapply[i] === req.decoded.username){
          user1.reqapply.splice(i, 1);
          break;
        }
      }
      user1.save(function(err,user1){
        if(err||!user1) return res.json(util.successFalse(err));
        else {
          res.json(util.successTrue("요청을 성공적으로 수락했습니다!"));
        }
      })
    })
})

// 3. 매칭 거절
router.put('/:username/reject', util.isLoggedin,
  function(req,res,next){
    // 피신청자 resapply에 신청자 id 제거 후 matchuser로 이동
    User.findOne({username:req.decoded.username})
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      for(var i = 0; i < user.resapply.length; i++){
        if (user.resapply[i] === req.params.username){
          user.resapply.splice(i, 1);
          break;
        }
      }
      user.save(function(err,user){
        if(err||!user) return res.json(util.successFalse(err));
      })
    })
    
    // 신청자 reqapply에 피신청자 id 제거 후 matchuser로 이동
    User.findOne({username:req.params.username})
    .exec(function(err,user1){
      if(err||!user1) return res.json(util.successFalse(err));
      for(var i = 0; i < user1.reqapply.length; i--){
        if (user1.reqapply[i] === req.decoded.username){
          user1.reqapply.splice(i, 1);
          break;
        }
      }
      user1.save(function(err,user1){
        if(err||!user1) return res.json(util.successFalse(err));
        else {
          res.json(util.successTrue("요청을 성공적으로 거절했습니다!"));
        }
      })
    })
})

module.exports = router;