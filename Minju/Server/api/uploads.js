var express    = require('express');
var router   = express.Router();
var multer = require('multer');

// 프로필 사진 업로드를 위한 업로드 api 베타
// 해당 코드를 users의 회원 가입 api에 녹여내면 됨

const ImageModel = require("../models/image.model");

// storage
var Storage = multer.diskStorage({
  destination:'uploads',
  filname:(req,file,cb)=>{
    cb(null,file.originalname);
  }
});

var upload = multer({
  storage:Storage
}).single('testImage')

router.post('/upload',(req,res)=>{
  upload(req,res,(err)=>{
    if(err){
      console.log(err)
    }
    else{
      const newImage = new ImageModel({
        name: req.body.name,
        image:{
          data:req.file.filename,
          contentType:'image/png'
        }
      })
      newImage
      .save()
      .then(()=>res.send('successfully uploaded'))
      .catch(err=>console.log(err));
    }
  })
})

module.exports = router;