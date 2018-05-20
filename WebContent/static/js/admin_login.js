$(document).ready(function(){
	$('#adminLoginForm').bootstrapValidator({
		message: 'This value is not valid',
        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证*/
        	userName: {/*键名username和input name值对应*/
                message: '用户名无效',
                validators: {
                	notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {/*长度提示*/
                        min: 4,
                        max: 20,
                        message: '用户名长度必须在4到20之间'
                    }/*最后一个没有逗号*/
                }
            },
            password: {
                message:'密码无效',
                validators: {
                	notEmpty: {
                        message: '密码不能为空'
                    },
                	regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
	                    regexp: /^[a-zA-Z0-9]{4,20}$/,
	                    message: '密码长度必须在4到20之间,只能是数字和字母组成'
	                }
                }
            }
        }
	});
	
	
	
	
	
	
});