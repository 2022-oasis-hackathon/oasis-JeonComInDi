var express  = require('express');
var router   = express.Router();
var User     = require('../models/User');
var util     = require('../util');
const PROMISE = require("bluebird");

// 1. 내가 신청한 목록 출력
router.get('/my_request', util.isLoggedin,
  function(req,res,next){
    console.log(req.decoded.username);
    User.findOne({username:req.decoded.username})
    .exec(
      function(err,user){
        global.n_list = [];
        const prom1 = new Promise((resolve, reject)=>{
          for(var i = 0; i < user.reqapply.length; i++){
            const prom = new Promise((resolve, reject)=>{
                console.log(user.reqapply[i]);
                User.findOne({username:user.reqapply[i]})
                .exec(
                  function(err,user1){
                    if(err||!user1) reject('err');
                    console.log(user1);
                    console.log(n_list);
                    resolve(user1)
                  }) 
                }
            )
            prom.then((user1)=>{
              n_list.push(user1)
            })    
          }
          resolve(n_list)
        }) 
        prom1.then((n_list)=>{
          res.json(util.successTrue(n_list))
        })    
    } 
  )}
)

  module.exports = router;

  // TODO : 반복문 동기화 처리