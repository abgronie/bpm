function initRequired(initSet){
   var setVal = initSet.setVal.split(",");
   for(var i = 0;i<setVal.length;i++){
      var validate = jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").attr('validate');
      if(isNotNull(validate)&&validate.indexOf("'required':'true'")==-1){
        validate = validate.substring(1,validate.length-1);
        validate = validate.substring(validate.length-1,validate.length)==","?validate:validate+",";
        validate = validate+"'required':'true'";
        validate = validate.substring(0,1)==","?validate.substring(1,validate.length):validate;
        validate = "{"+validate+"}";
        jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").attr('validate',validate);
      }
   }
}

function setRedSpan(initSet){
   var setVal = initSet.setVal.split(",");
   for(var i = 0;i<setVal.length;i++){
        var perTd =  jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").parents("td").prev();
		var size = perTd.find(".red").size();
		if(size == 0){
			perTd.append("<span class='red'>*</span>");
		}
   }
}

function reMoveRequired(initSet){
       var setVal = initSet.setVal.split(",");
       for(var i = 0;i<setVal.length;i++){
         var validate = jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").attr('validate');
		  if(isNotNull(validate)&&validate.indexOf("'required':'true'")==-1){
			  validate = validate.replaceAll(",'required':'true'","");
			  validate = validate.replaceAll("'required':'true',","");
			  validate = validate.replaceAll("'required':'true'","");
			  jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").attr('validate',validate);
		  }
       }
    }

function reMoveRedSpan(initSet){
   var setVal = initSet.setVal.split(",");
   for(var i = 0;i<setVal.length;i++){
        var perTd =  jQuery(":input[name='"+initSet.tabPre+":"+setVal[i]+"']").parents("td").prev();
		var size = perTd.find(".red").size();
		if(size == 1){
			perTd.find(".red").remove();
		}
   }
}
	
function show(id){
       jQuery("#"+id).css({"display":""});
}


function hide(id){
       jQuery("#"+id).css({"display":"none"});
}


String.prototype.replaceAll = function(s1,s2){
     return this.replace(new RegExp(s1,"gm"),s2);
 }
 
 function initdisAbled(id){
  jQuery("#"+id).hide();
  jQuery("#"+id).find(":input").each(function(index,Dom){
    jQuery(Dom).attr('disabled','true');
  });
}

function initenAbled(id){
  jQuery("#"+id).show();
  jQuery("#"+id).find(":input").each(function(index,Dom){
    jQuery(Dom).removeAttr('disabled','false');
  });
}

function isNotNull(str){
  if(null==str||""==str||"undefined"==str){
    return false;
  }
  return true;
}

function isNull(str){
  if(null==str||""==str||"undefined"==str){
    return true;
  }
  return false;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var min = date.getMinutes();
    var sec = date.getSeconds();
    if(min>=1&&min<=9){
      min = "0"+min;
    }
    
   if(sec>=1&&sec<=9){
      sec = "0"+sec;
    }
   var currentdate = year + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + min
            + seperator2 + sec;
    return currentdate;
}

function accAdd(arg1, arg2) {
  var r1, r2, m;
  try {
      r1 = arg1.toString().split(".")[1].length
  } catch (e) {
      r1 = 0
  }
  try {
      r2 = arg2.toString().split(".")[1].length
  } catch (e) {
      r2 = 0
  }
  m = Math.pow(10, Math.max(r1, r2))
  return (arg1 * m + arg2 * m) / m
}
//给Number类型增加一个add方法，调用起来更加方便。 
Number.prototype.add = function (arg) {
  return accAdd(arg, this);
}

function getAllAdd(name){
    var total = 0;
	var names = name["setText"].split(",");
	  for(var i=0;i<names.length;i++){
		var value = jQuery("[name='"+name.name+names[i]+"']").val();
		if(isNotNull(value)&&"请选择"!=value){
			total = accAdd(parseFloat(total),parseFloat(value));
		}
	 } 
	return total;
}

//减法函数，用来得到精确的加法结果 
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。 
//调用：accAdd(arg1,arg2) 
//返回值：arg1加上arg2的精确结果 


function accSub(arg1, arg2) {
  var r1, r2, m, n;
  try {
      r1 = arg1.toString().split(".")[1].length
  } catch (e) {
      r1 = 0
  }
  try {
      r2 = arg2.toString().split(".")[1].length
  } catch (e) {
      r2 = 0
  }
  m = Math.pow(10, Math.max(r1, r2));
  //last modify by deeka
  //动态控制精度长度
  n = (r1 >= r2) ? r1 : r2;
  return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

//给Number类型增加一个add方法，调用起来更加方便。 
Number.prototype.Sub = function (arg) {
  return accSub(arg, this);
}

function accMul(arg1, arg2) {
  var m = 0,
      s1 = arg1.toString(),
      s2 = arg2.toString();
  try {
      m += s1.split(".")[1].length
  } catch (e) {}
  try {
      m += s2.split(".")[1].length
  } catch (e) {}
  return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
}
//给Number类型增加一个mul方法，调用起来更加方便。 
Number.prototype.mul = function (arg) {
  return accMul(arg, this);
}

function initShr(name){
   var date = new Date();
   var dqsj = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
   var dqjd = jQuery("#currentNode").val();
   var dqxm = jQuery("input[name='curUserName']").val();
   jQuery("input[name='"+name+dqjd+"Shr']").val(dqxm);
   jQuery("input[name='"+name+dqjd+"Shsj']").val(dqsj);
}

function initShowShr(name){
  var names = name["setText"].split(",");
  for(var i=0;i<names.length;i++){
    var value = jQuery("input[name='"+name.name+names[i]+"']").val();
	if(isNotNull(value)){
		jQuery("#"+names[i]+"Span").html(value);
	}else{
		jQuery("#"+names[i]+"Span").html("        ");
	}
  } 
}

function reMoveSelect(name){
  var dqjd = jQuery("#currentNode").val();
  var start = jQuery("#startNode").size();
  if(isNull(dqjd)&&(start==0)){
     var names = name["setText"].split(",");
	 for(var i=0;i<names.length;i++){
		var num = jQuery("input[name='"+name.name+names[i]+"']").size();
		if(num==0){
		  jQuery("#"+names[i]+"P").hide();
		}
	 } 
  }
}


function initSqxn(zdName,setText){
	var querydata = {};
	condition = {alias:"dqxnxqdzb",page:1,pagesize:10,querydata:querydata};
		DoQuery(condition,function(data){
		    if(null!=data.list&&data.list.length==1){
			var dqxn = data.list[0]["dqxn"];
			var value = jQuery("input[name='"+zdName+"']").val();
			if(isNotNull(value)){
				dqxn = value;
			}
			jQuery("input[name='"+zdName+"']").val(dqxn);
			if(isNotNull(setText)){
			  jQuery("#"+setText).html(dqxn);
			}
		    }
		},true);
}

function initPjxn(zdName,setText){
	var querydata = {};
	condition = {alias:"pjxn",page:1,pagesize:10,querydata:querydata};
		DoQuery(condition,function(data){
		    if(null!=data.list&&data.list.length==1){
			var dqxn = data.list[0]["pjxn"];
			var value = jQuery("input[name='"+zdName+"']").val();
			if(isNotNull(value)){
				dqxn = value;
			}
			jQuery("input[name='"+zdName+"']").val(dqxn);
			if(isNotNull(setText)){
			  jQuery("#"+setText).html(dqxn);
			}
		    }
		},true);
}

function checkisCan(params){
        var flag = false;
	condition = {alias:params.getID,page:1,pagesize:10,querydata:params.querydata};
		DoQuery(condition,function(data){
		    if(data.totalCount==0){
			    flag  =  true;
		    }else{
			  flag = false;
		    }
		},true);
    return flag;
}

function checkJg(params){
        var flag = true;
	condition = {alias:params.getID,page:1,pagesize:10,querydata:params.querydata};
		DoQuery(condition,function(data){
		    if(data.totalCount==0){
			    flag  =  false;
		    }
		},true);
    return flag;
}

function getValue(params){
    var isSync = params.isSync;
	if(isNull(isSync)){
	   isSync = false;
	}
	condition = {alias:params.getID,page:1,pagesize:10,querydata:params.querydata};
		DoQuery(condition,function(data){
		   if(null!=data.list&&data.list.length==1){
		    var values = params.setValue.split(",");
			var texts =  params.setText.split(",");
			for(var i=0;i<values.length;i++){
				var value = jQuery("input[name='"+params.name+values[i]+"']").val();
			  if(isNull(value)){
				jQuery("input[name='"+params.name+values[i]+"']").val(data.list[0][texts[i]]);
				jQuery("#"+values[i]+"Value").html(data.list[0][texts[i]]);
			  }else{
			    jQuery("#"+values[i]+"Value").html(value);
			  }
			}
		   }
		},isSync);
}


function setValue(name){
  var names = name["setText"].split(",");
  for(var i=0;i<names.length;i++){
    var value = jQuery("input[name='"+name.name+names[i]+"']").val();
	if(isNotNull(value)){
		jQuery("#"+names[i]+"Value").html(value);
	}
  } 
}

//参数{name:'',setText:'',isEdit:'',isSync:'',setValue:''}
function setXsxx(data){
   var name = data.name;
   var setText = data.setText.split(",");
   var isEdit = data.isEdit.split(",");
   var isSync = data.isSync;
   var setValue = data.setValue.split(",");
   var xh = jQuery("input[name='"+name+":xh']").val();
   var xm = jQuery("input[name='"+name+":xm']").val();
   if(null==xm||''==xm){
	   var querydata = '{"XH":'+xh+'}';
	   condition = {alias:"hqxsjbxx",page:1,pagesize:10,querydata:querydata};
	   DoQuery(condition,function(data){
		 if(null!=data.list||data.list.length==1){
		   for(var i=0;i<setText.length;i++){
		      if(isEdit[i]=='false'){
			  jQuery("input[name='"+name+":"+setText[i]+"']").val(data.list[0][setValue[i]]);
                jQuery("#"+setText[i]+"Value").html(data.list[0][setValue[i]]);
			  }else{
			    jQuery("input[name='"+name+":"+setText[i]+"']").val(data.list[0][setValue[i]]);
			  }
		   }
		 }
		 },isSync);
    }else{
	   for(var i=0;i<setText.length;i++){
		  if(isEdit[i]=='false'){
			jQuery("#"+setText[i]+"Value").html(jQuery("input[name='"+name+":"+setText[i]+"']").val());
		  }
	   }
	}
}


//喻鑫源-------------------
function setDqxn(zdName){

	var querydata = {};
	condition = {alias:"dqxnxqdzb",page:1,pagesize:10,querydata:querydata};
		DoQuery(condition,function(data){
		    if(null!=data.list&&data.list.length==1){
			var dqxn = data.list[0]["dqxn"];
			jQuery("input[name='"+zdName+"']").val(dqxn);
              if(jQuery("input[name='"+zdName+"']").length == '1'){
                  jQuery("#xn").text(dqxn);
              }
			
		    }
		},true);
}


function getKnsxx(para){
	var idstr = para.idstr;
	var namestr = para.namestr;
	var xh = para.xh;
	var xn = para.xn;
	var querydata = '{"XN":'+para.xn+',"XH":'+para.xh+'}';
	condition = {alias:"knsjgzbcx",page:1,pagesize:10,querydata:querydata};
        
	DoQuery(condition,function(data){
	  
	  if(data.totalCount != null && data.totalCount !="" &&  data.totalCount >= 1){
		 var knsdj = data.list[0]["knsdj"];
		 jQuery("input[name='"+namestr+"']").val(knsdj);
		  if(jQuery("input[name='"+namestr+"']").length == '1'){
			jQuery("#"+idstr).text(knsdj);
		  }
		 
	  }else{
		 jQuery("input[name='"+namestr+"']").val("家庭经济不困难");
		 jQuery("#"+idstr).text("家庭经济不困难");
	  }
	},true);
	}

function pdsfkns(str){
	if(jQuery("#"+str).text() == "家庭经济不困难"){
	  return false;
	}else{
	 return true;
	}
}

//默认显示3行 table-tr-td设置值 setTextval= "xm,nl,cw,gzhxxdw,zy,nsr,jkzk"字符串数组
 //数据库值dataval="xm,nl,cw,gzhxxdw,zy,nsr,jkzk"  setTextval和dataval需要一一对应，否则数据取不到
 //两个数组合入一个map参数para{setTextval:"",dataval:"",xh:"",formName:""}
 //直接写死取值三行数据
 function getJtcyqkZb(para){
  var querydata = '{"XH":'+para.xh+'}';
  var flag = true;
  condition = {alias:"jtcysjdqcx",page:1,pagesize:10,querydata:querydata};
    DoQuery(condition,function(data){
          if(data.totalCount != null && data.totalCount !="" &&  data.totalCount >= 1){
            var setTextval = para.setTextval.split(",");
	        var dataval = para.dataval.split(",");
	        var setLen = setTextval.length;
	        var formName = para.formName;
	        for(var i=0;i<3;i++){
	           for(var j=0;j<setLen;j++){
	            jQuery("input[name='m:"+formName+":"+setTextval[j]+(i+1)+"']").val(data.list[i][dataval[j]]);
	           }
	        }
            
          }   
		},true);
}


//晨练状态获取
function getClzt(para){
   var querydata = '{"XH":'+para.xh+'}';
  var flag = true;
  var formname = para.formname;
  var zd = para.zd;
  var clzt = "";
  condition = {alias:"cphgqkcx",page:1,pagesize:200,querydata:querydata};
    DoQuery(condition,function(data){

      if(data.totalCount >= 1){
        	  for(var i=0;i<data.totalCount;i++){
                  clzt +=data.list[i]['clzt'];
        		  if(i == data.totalCount-1){
                    if(data.list[i]['clzt'].indexOf("不合格") != -1){
                      alert(data.list[i]['clzt']);
                      clzt = "否";
                    }else{
                      clzt = "是";
                    }
        		  }
        	  }
          }else{
        	  clzt = "否";
          }
		},true);
    
    jQuery("input[name='m:"+formname+":"+zd+"']").val(clzt);
    jQuery("#"+para.textid).text(clzt);
}


