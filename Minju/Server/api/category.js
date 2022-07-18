var express  = require('express');
var router   = express.Router();
var User     = require('../models/User');
var mongoose   = require('mongoose');
var util     = require('../util');
const { db } = require('../models/User');

// 특정 카테고리의 유저 보기
router.get('/:talent', util.isLoggedin,
function(req,res,next){
  db.collection("appusers").find({restalent : req.params.talent}).toArray(function(err,result){
    if (err) return res.json(util.successFalse(err));
    res.json(util.successTrue(result))
  })
})

// TODO 카테고리별로 router 구현
module.exports = router;