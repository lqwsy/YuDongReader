    /*
     *图片验证
     */
    function photoCheck(obj){
        var ff = $("#photoSrc").val();
        if(ff == null || ff == ""){
            alert("请选择文件"); 
            return;
        }else if(!/.(jpg|jpeg|png)$/.test(ff)){
            alert("图片类型必须是jpeg,jpg,png中的一种");
            return;
        }
        photo_flag = true;
        //设置限制图像的大小为10MB，这里你可以自己设置
        var fSize = 1024 * 20;
        //限制图片宽高
        var fHeight = 450;
        var fWidth = 550;
        
        var fileType;
        var fileSize;
        var filePath;
        var img = new Image();
        //显示图片
        if (obj.files) {
            //用来把文件读入内存，并且读取文件中的数据，要注意的是只有 Firefox 3.6+ 和 Chrome 6.0+ 实现了 FileReader 接口。
            var reader = new FileReader();
            //获取文件的对像
            var thisFile = obj.files[0];
            //获取上传文件的类型，一般来说，如果类型是image/jpeg,.jpg,.gif等等图片格式的话就是合理的
            fileType = thisFile.type;
            //获取当前上传的文件的大小
            fileSize=thisFile.size;
            /*readAsDataURL：该方法将文件读取为一段以 data: 开头的字符串，这段字符串的实质就是 Data URI，Data URI是一种将小
            文件直接嵌入文档的方案。这里的小文件通常是指图像与 html 等格式的文件*/
            reader.readAsDataURL(thisFile);
            //文件读取成功完成时触发
            reader.onloadend = function(event){
                // 图像的路径
                img.src = event.target.result;
                // 图片加载完毕后
                img.onload = function(event) {
                    //效验图片规格
                    specification(img);
                    filePath = this.src;
                    if(photo_flag){
                        //设置图片为当前上传的图片路径
                        $("#imgShow").attr("src", filePath);
                    }else{
                        //否则设置默认的图片
                        $("#imgShow").attr("src", "default.png");
                    }
                }
            }
              
        }else{// 如果是ie浏览器
            
            //选择当前全部文本内容
            obj.select();
            /*
                为选择的内容创建一个Range对象，在.text转换成文本
                什么是Range？https://my.oschina.net/122612475/blog/286302
                所谓"Range"，是指HTML文档中任意一段内容。一个Range的起始点和结束点位置任意，甚至起始点和结束点可
                以是一样的（也就是空Range）。最常见的Range是用户文本选择范围(user text selection)。当用户选择了
                页面上的某一段文字后，你就可以把这个选择转为Range。当然，你也可以直接用程序定义Range
             */
            var path = document.selection.createRange().text;
            img.src = path; 
              //效验图片规格
            specification(img);
              //取出文件后缀
            var fileType = path.substring(path.length-4,path.length);
            if(img.readyState == "complete") {
                //图片加载完毕,获取图片的大小
                fileSize = img.fileSize;
            }else{
                //当的该对象的 load state 改变时，会触发此事件
                img.onreadystatechange = function(){
                    // 当图片load完毕
                     if(img.readyState=='complete'){
                         fileSize = img.fileSize;
                         if(fileSize > fSize){
                            photo_flag = false;
                            $("#imgShow").attr("src", "default.png");
                            return;
                         }
                     }
                }
            }
            if (path) {
                filePath = path;
            }
        }
        
           //图片格式的判断
          if(
                 fileType != ".jpg" && fileType != ".JPG" && fileType != "image/jpeg" &&
                 fileType != ".png" && fileType != ".PNG" && fileType != "image/png"  &&
                 fileType != ".gif" && fileType != ".GIF" && fileType != "image/gif"  
           ){
              alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
             photo_flag = false;
             $("#imgShow").attr("src", "default.png");
             return;
         }
           //效验图片内存大小
         if(fileSize > fSize){
             alert("图片太大了！");
             photo_flag = false;
             $("#imgShow").attr("src", "default.png");
             return;
         }
         if(photo_flag){
             $("#imgShow").attr("src", filePath);
         } else {
             $("#imgShow").attr("src", "default.png");
         }
         // 匿名函数:效验图片规格
         var specification = function(image){
             if(image.width >= fWidth || image.height >= fHeight){
                 alert("*提示：文件大小不对。您只能上传小于等于"+fWidth+"*"+fHeight+"尺寸的图片");
                 $("#imgShow").attr("src", "default.png");
                 return;
             }
         }
    }
    
	$("#upload-btn").click(function(){
        var src=canvas.toDataURL("image/jpeg");
        $.ajax({
            url:"uploadImage",
            type:"POST",
            data:{img:src},
            dataType:"json",
            success:function(data){
                alert("上传成功！文件名："+data);
            }
        });
    });