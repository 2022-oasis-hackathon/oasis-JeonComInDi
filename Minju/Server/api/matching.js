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
    if(!(user.reqapply.includes(req.params.username))){
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
              res.json(util.successTrue("Apply success!"));
            }
          })
        })
    }
    // 이미 신청했으면
    else {
      return res.json(util.applyFalse("Already requested!"))
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
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      user.matchuser.push(req.decoded.username);
      for(var i = 0; i < user.reqapply.length; i--){
        if (user.reqapply[i] === req.decoded.username){
          user.reqapply.splice(i, 1);
          break;
        }
      }
      user.save(function(err,user){
        if(err||!user) return res.json(util.successFalse(err));
        else {
          res.json(util.successTrue("Match success!"));
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
    .exec(function(err,user){
      if(err||!user) return res.json(util.successFalse(err));
      for(var i = 0; i < user.reqapply.length; i--){
        if (user.reqapply[i] === req.decoded.username){
          user.reqapply.splice(i, 1);
          break;
        }
      }
      user.save(function(err,user){
        if(err||!user) return res.json(util.successFalse(err));
        else {
          res.json(util.successTrue("Reject success!"));
        }
      })
    })
})

module.exports = router;