//自定义验证
$.extend($.fn.validatebox.defaults.rules, {
    eqPassword: {
		validator: function(value, param){
			return value == $(param[0]).val();
		},
		message: '密码不一致'
    },
    phoneNum: { //验证手机号   
        validator: function(value, param){
         return /^1[3-8]+\d{9}$/.test(value);
        },    
        message: '请输入正确的手机号码。'   
    },
    telNum:{ //既验证手机号，又验证座机号
        validator: function(value){ 
            return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
           },    
           message: '请输入正确的电话号码。' 
   }  
});

