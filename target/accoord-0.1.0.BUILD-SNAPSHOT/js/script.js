var dialogNo=1;
var GlobalDialog={};


/**
 * Some variables for form submit through ajax and it s response handling
 * 
 */
var _STATUS=false;
var _STATUS_MESSAGE="";
var _STATUS_MESSAGE_INPUT={};
var _ST_MSG_STATUS="ST_MSG_STATUS";
var _ST_MSG_MESSAGE="ST_MSG_MESSAGE";
var _ST_MSG_TYPE="ST_MSG_TYPE";
var _REQ_STAT="REQ_STAT";

jQuery(document).ready(function()
		{
			showStatusMessage();
			if(document.getElementById("username")!="undefined"&& document.getElementById("username")!=null)document.getElementById("username").focus();
		});



function showErrorMsg(name,message,isclientmsg)
{
	if(isclientmsg)
	{
		if(message!=null)
		{
			document.getElementById("error_client_"+name).innerHTML="<span class='errorspan'>"+message+"</span>";
		}
		document.getElementById("error_client_"+name).style.display="block";
	}
	else
	{
		if(message!=null)
		{
			document.getElementById("error_server_"+name).innerHTML="<span class='errorspan'>"+message+"</span>";
		}
		document.getElementById("error_server_"+name).style.display="block";
	}
}
function showInputStatusMessage(msgs)
{
	for(var i in msgs)
	{
		showErrorMsg(i, msgs[i], false);
	}
}

function readCookie(cookieName) 
{
	 var theCookie=""+document.cookie;
	 var ind=theCookie.indexOf(cookieName);
	 if (ind==-1 || cookieName=="") return ""; 
	 var ind1=theCookie.indexOf(';',ind);
	 if (ind1==-1) ind1=theCookie.length; 
	 var val=theCookie.substring(ind+cookieName.length+1,ind1);
	 val=val.trim();
	 if(val.indexOf("\"")==0)
	 {
		 val=val.substring(1,val.length);
	 }
	 if(val.indexOf("\"")==(val.length-1))
	 {
		 val=val.substring(0,val.length-1);
	 }
	
	 var returnstr= URLDecode(val);
	 return returnstr;
}
function validateOldPassword(el)
{
	var str=el.value.trim();
	if(str!=null && str!="undefined" && str!="")
	return true;
	return false;
}
function validateOldPasswordFailed(el)
{
	showErrorMsg(el.id,null,true);
		
}
function validateNewPassword(el)
{
	var str=el.value.trim();
	if(str!=null && str!="undefined" && str!="")
	return true;
	return false;
}
function validateNewPasswordFailed(el)
{
	showErrorMsg(el.id,null,true);
		
}
function validateConfirmNewPassword(el)
{
	var str=el.value.trim();
	if(str!=null && str!="undefined" && str!="")
	{
		var str2=document.getElementById("newpassword").value.trim();
		if(str2==str)
		return true;
	}
	return false;
}
function validateConfirmNewPasswordFailed(el)
{
	showErrorMsg(el.id,null,true);
		
}

if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}
function validateUsername(el)
{
	var s=el.value.trim();
	if(s!=null && s!="undefined" && s!="")
		return true;
	return false;
}
function validateUsernameFailed(el)
{
	showErrorMsg(el.id,null,true);
}
function isRequestSuccess()
{
	var statusmsg=readCookie(_REQ_STAT);
	if("true"==statusmsg)
	{
		return true;
	}
	return false;
}
function validateEmail(el)
{
	var str=el.value.trim();
	if(str==""|| str=="undefined" || str==null)
		return false;

	if(checkEmail(str))
	{
		return true;
	}
	else
	{
		return false;
	}
}
function validateEmailFailed(el)
{
	showErrorMsg(el.id,null,true);
		
}
function validateForgotPasswordForm(el)
{
	if(validateNormalForm(el))
	{
		return true;
	}
	return false;
}
function showStatusMessage()
{
	var statusmsg=readCookie(_ST_MSG_MESSAGE); 
	var statusmsgstatus=readCookie(_ST_MSG_STATUS);
	var statusmsgtype=readCookie(_ST_MSG_TYPE);
	
	var status=statusmsgstatus.split("::")[0];
	if(status=="read" || statusmsg==null || statusmsg=="undefined" || statusmsg=="")
	{
		return;
	}
	
	var temp=statusmsgstatus.split("A")[1];
	var isTrue=true;
	if(temp=="false")
	{
		isTrue=false;
	}

	var days = 1000;

    // Calculate the expiration date
    var expires = new Date ();
    expires.setTime(expires.getTime() + days * (24 * 60 * 60 * 1000));
	SetCookie(_ST_MSG_STATUS, "readA"+temp,expires);
	deleteCookie(_ST_MSG_MESSAGE);
	deleteCookie(_ST_MSG_STATUS);
	deleteCookie(_ST_MSG_TYPE);
	if(statusmsgtype=="top")
	{
		$.jGrowl(statusmsg,{sticky:isTrue,position:'center',theme:'piservejgrowl'});
	}
	else
	{
		showInputStatusMessage(getAssociativeArray(statusmsg));
	}
}
/**
 * Deletes cookie with the name and path
 */
function deleteCookie(name,path,win) 
{  
  var doc = (win == null)?document:win.document;
  var exp = new Date();  
  exp.setTime (exp.getTime() - 1);  
  var cval = getCookie(name);  
  if(!path){path="/";}
  doc.cookie = name + "=" + cval + "; expires=" + exp.toGMTString()  +"; path=" + path;
}
function getCookie (name,win) 
{
  var doc = (win == null)?document:win.document;

  var arg = name + "=";  
  var alen = arg.length;  
  var clen = doc.cookie.length;  
  var i = 0;  
  while (i < clen) {    
    var j = i + alen;    
    if (doc.cookie.substring(i, j) == arg)      
      return getCookieVal (j,doc);    
    i = doc.cookie.indexOf(" ", i) + 1;    
    if (i == 0) break;   
  }  
  return null;
}

function getCookieVal(offset,doc) 
{
  var endstr = doc.cookie.indexOf (";", offset);
  if (endstr == -1)
    endstr = doc.cookie.length;
  return decodeURIComponent(doc.cookie.substring(offset, endstr));
}

function SetCookie(cookieName, cookieData, expireDate) {
    document.cookie = cookieName + "=" + escape(cookieData) + "; expires=" + expireDate.toGMTString();
}  

function showDialog(options){

	var associativeArray=getAssociativeArray(options);
	associativeArray["autoOpen"]=false;
	var dialog = $('<div></div>').html('This dialog will show every time!').dialog(associativeArray);
	dialog.dialog('open');
}

function showURLInDialog(url,options,forcerequest)
{
	if(options==null || options=="undefined")
	{
		options="autoOpen=false";
	}
	var associativeArray=getAssociativeArray(options);
	associativeArray["autoOpen"]=false;
	if(GlobalDialog[url]!=null && GlobalDialog[url]!="undefined" && forcerequest!="true")
	{
		var d=GlobalDialog[url];
		d.dialog("open");
		return;
	}
	closeDialog();
	$.ajaxSetup ({  
		cache: false  
	});  
	var dialog = $("<div id='dialog"+dialogNo+"'></div>").html('').dialog(associativeArray);
	var ajax_load = "loading...";  
	var loadUrl = url;  
	var no=dialogNo;
	$("#dialog"+dialogNo).html(ajax_load).load(loadUrl,{"dialogno":dialogNo},null);  
	dialog.dialog('open');
	GlobalDialog[url]=dialog;
	GlobalDialog[dialogNo]=dialog;
	GlobalDialog[dialogNo+"force"]=forcerequest;
	dialogNo++;
}
function executeScriptsInResponse(responseText)
{
	var source = responseText;
	var scripts = new Array();

	while(source.indexOf("<script") > -1 || source.indexOf("</script") > -1) {
		var s = source.indexOf("<script");
		var s_e = source.indexOf(">", s);
		var e = source.indexOf("</script", s);
		var e_e = source.indexOf(">", e);
		
		// Add to scripts array
		scripts.push(source.substring(s_e+1, e));
		// Strip from source
		source = source.substring(0, s) + source.substring(e_e+1);
	}
	
	// Loop through every script collected and eval it
	for(var i=0; i<scripts.length; i++) {
		try {
			eval(scripts[i]);
		}
		catch(ex) {
				alert("Problem in script please contact vendor");
		}
	}

}
function clearErrorDivs(el)
{
	if(el==null || el=="undefined")
	{
		return;
	}
	var ellist=getChildElsWithAttr(el,"errordiv","true");
	for(var i=0;i<ellist.length;i++)
	{
		ellist[i].style.display="none";
	}
}
function URLDecode(psEncodeString)
{
  // Create a regular expression to search all +s in the string
  var lsRegExp = /\+/g;
  // Return the decoded string
  return unescape(String(psEncodeString).replace(lsRegExp, " "));
}
function getAssociativeArray(options)
{
	var associativeArray={};
	var optionArray=options.split(",");
	for(var i=0;i<optionArray.length;i++)
	{
		var option=optionArray[i];
		var split=option.split("::");
		var name=split[0];
		var value=split[1];
		if(!isNaN(value))
		{
			value=parseInt(value);
		}
		if(value=="true"){value=true;}
		if(value=="false"){value=false;}
		associativeArray[name]=value;
	}	
	return associativeArray;
}
function validateNormalForm(formel)
{
	clearErrorDivs(formel);
	var els=getChildElsWithAttr(formel,"validatefunc","*");
	var fail=false;
	for(var i=0;i<els.length;i++)
	{
		var el=els[i];
		var fn=el.getAttribute("validatefunc");
		var bool=eval(fn)(el);
		if(bool==false)
		{
			var handle=el.getAttribute("validationfailedfunc");
			if(handle!=null && handle!="undefined")
			{
				eval(handle)(el);
			}
			fail=true;
		}
		else
		{
			var ids=el.getAttribute("id");
			if(document.getElementById("error_client_"+ids)!=null && document.getElementById("error_client_"+ids)!="undefined")
			document.getElementById("error_client_"+ids).style.display="none";
			if(document.getElementById("error_server_"+ids)!=null && document.getElementById("error_server_"+ids)!="undefined")
			document.getElementById("error_server_"+ids).style.display="none";
		}
	}

	if(fail){return false;}
	return true;

}
function validateLoginForm(formel)
{
	clearErrorDivs(formel);
	var els=getChildElsWithAttr(formel,"validatefunc","*");
	var fail=false;
	for(var i=0;i<els.length;i++)
	{
		var el=els[i];
		var fn=el.getAttribute("validatefunc");
		var bool=eval(fn)(el);
		if(bool==false)
		{
			var handle=el.getAttribute("validationfailedfunc");
			if(handle!=null && handle!="undefined")
			{
				eval(handle)(el);
			}
			fail=true;
		}
		else
		{
			var ids=el.getAttribute("id");
			if(document.getElementById("error_client_"+ids)!=null && document.getElementById("error_client_"+ids)!="undefined")
			document.getElementById("error_client_"+ids).style.display="none";
			if(document.getElementById("error_server_"+ids)!=null && document.getElementById("error_server_"+ids)!="undefined")
			document.getElementById("error_server_"+ids).style.display="none";
		}
	}

	if(!fail)
		{
			document.getElementById("j_username").value=document.getElementById("username").value+"/"+document.getElementById("organization").value;
		}
	
	if(fail){return false;}
	return true;

}

function validateForm(arr,$form,options)
{
	var formel=$form[0];
	clearErrorDivs(formel);
	return validateNormalForm(formel);
}

function closeDialog()
{
	for(var i=dialogNo-1;i>0;i--)
	{
		var d=GlobalDialog[i];
		if(d!=null && d!="undefined")
		{
			d.dialog("close");
		}
		var force=GlobalDialog[i+"force"];
		if(force=="true")
		{
			var el=d[0];
			el.innerHTML="";
		}
	}
}
function openUrl(url)
{
	location.href=url;
}


function filterElements(elList,elType,attrName,attrValue)
{
	var filteredList = new Array();
	var j = 0;
	var anyVal  = (attrValue == "*");
	for(var i =0;i < elList.length ; i++)
	{
		if((elType == null) || (elList[i].nodeName == elType))
		{
			if((attrName == null)|| (elList[i].getAttribute(attrName) == attrValue)
					|| ((anyVal) &&(elList[i].getAttribute(attrName) != null)))
			{
				filteredList[j++] = elList[i];
			}
		}
	}
	return filteredList;
}
function getFirstMatchingElement(elList,elType,attrName,attrValue)
{
	for(var i =0;i < elList.length ; i++)
	{
		if((elType == null) || (elList[i].nodeName == elType))
		{
			if((attrName == null)|| (elList[i].getAttribute(attrName) == attrValue)
					|| ((attrValue == "*") &&(elList[i].getAttribute(attrName) != null)))
			{
				return elList[i];
			}
		}
	}
	return null;
}


function getChildElsWithAttr(parentEl,attrName,attrValue)
{
	var childNodes = parentEl.getElementsByTagName("*");
	return this.filterElements(childNodes,null,attrName,attrValue);
}
function getFirstChild(parentEl,attrName,attrValue,filterAttr,filterValue)
{
	var b;
	var a=DOMUtils.getChildElsWithAttr(parentEl,attrName,attrValue);
	for(i=0 ; i<a.length ; i++)
	{       
		b=a[i].parentNode;
		while(b != parentEl)
		{	
			if(b.getAttribute(filterAttr) == filterValue)
			{
				break;
			}
			b = b.parentNode;
		}
		if(b == parentEl) 
		{
			return a[i];
		}
	}
}

function getParentWithAttr(childNode,parentAttrName)
{
	var parNode = childNode.parentNode;
	while((parNode != null) && (parNode.getAttribute != null))
	{
		if(parNode.getAttribute(parentAttrName) != null)
		{
			return parNode;
		}
		parNode = parNode.parentNode;
	}
	return null;
}

function getParentWithAttrValue(childNode,parentAttrName,attrValue)
{
	var parentNode = childNode.parentNode;
	while((parentNode != null) && (parentNode.getAttribute != null))
	{
		if(parentNode.getAttribute(parentAttrName) == attrValue)
		{
			return parentNode;
		}
		parentNode = parentNode.parentNode;
	}
	return parentNode;
}

function isAlphaNumericWithMinusAndSpace(str)
{
	var objRegExp = /^[a-zA-Z0-9\-_\ ]+$/;
	if(objRegExp.test(str)){
		return true;
	}
	return false;
}

//Declaring required variables
var digits = "0123456789";
//non-digit characters which are allowed in phone numbers
var phoneNumberDelimiters = "()- ";
//characters which are allowed in international phone numbers
//(a leading + is OK)
var validWorldPhoneChars = phoneNumberDelimiters + "+";
//Minimum no of digits in an international phone no.
var minDigitsInIPhoneNumber = 10;

function isInteger(s)
{   var i;
for (i = 0; i < s.length; i++)
{   
	// Check that current character is number.
	var c = s.charAt(i);
	if (((c < "0") || (c > "9"))) return false;
}
// All characters are numbers.
return true;
}
function trim(s)
{   var i;
var returnString = "";
// Search through string's characters one by one.
// If character is not a whitespace, append to returnString.
for (i = 0; i < s.length; i++)
{   
	// Check that current character isn't whitespace.
	var c = s.charAt(i);
	if (c != " ") returnString += c;
}
return returnString;
}
function stripCharsInBag(s, bag)
{   var i;
var returnString = "";
// Search through string's characters one by one.
// If character is not in bag, append to returnString.
for (i = 0; i < s.length; i++)
{   
	// Check that current character isn't whitespace.
	var c = s.charAt(i);
	if (bag.indexOf(c) == -1) returnString += c;
}
return returnString;
}

function checkInternationalPhone(strPhone){
	var bracket=3
	strPhone=trim(strPhone)
	if(strPhone.indexOf("+")>1) return false
	if(strPhone.indexOf("-")!=-1)bracket=bracket+1
	if(strPhone.indexOf("(")!=-1 && strPhone.indexOf("(")>bracket)return false
	var brchr=strPhone.indexOf("(")
	if(strPhone.indexOf("(")!=-1 && strPhone.charAt(brchr+2)!=")")return false
	if(strPhone.indexOf("(")==-1 && strPhone.indexOf(")")!=-1)return false
	s=stripCharsInBag(strPhone,validWorldPhoneChars);
	return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}
function validatePhoneNo(el)
{
	if(el.value==null || el.value=="" || el.value=="undefined")
	{
		return true;
	}
	return checkPhone(el.value);
}
function validateEmail(el)
{
	if(el.value==null || el.value=="" || el.value=="undefined")
	{
		return true;
	}
	return checkEmail(el.value);
}

function handleInputEmpty(el)
{
	showErrorMsg(el.getAttribute("name"),null,true);
}
function handleSelectEmpty(el)
{
	showErrorMsg(el.getAttribute("name"),null,true);
}

function checkIfInputEmpty(el)
{
	
	if(el.value==null || el.value=="" || el.value=="undefined")
	{
		return false;
	}
	return true;
}
function checkIfSelectEmpty(el)
{
	if(el.value=="null" || el.value==null || el.value=="" || el.value=="undefined")
	{
		return false;
	}
	return true;
}
function checkPhone(str) 
{
		var Phone=str;
		if ((Phone==null)||(Phone=="")){
			return false;
		}
		if (checkInternationalPhone(Phone)==false){
				return false
		}
		return true
}

function checkEmail(str)
{
	var filter = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9-])+([.]{1,1})+([a-zA-Z0-9]{2,4})+$/;
	if (!filter.test(str)) 
	{
		return false;
	}
	return true;
}

function cancelRowClickSelection(rowid, e) 
{
	
	var target=e.originalTarget;
	var str=target.innerHTML;
	if(target.classList!=null && target.classList!="undefined" && target.classList[0]!=null && target.classList[0]!="undefined")
	{
		if(target.classList[0]=="cbox")
		{
			return true;
		}
	}
	if(str.indexOf("input")!=-1 || str.indexOf("INPUT")!=-1)
	{
		return true;
	}
	return false; 
}

function checkDate(el)
{
	if(!checkIfInputEmpty(el))
	{
		return false;
	}
	var date=el.value;
	var datearr=date.split("/");
	var month=parseInt(datearr[1]);
	var day=parseInt(datearr[0]);
	var year=parseInt(datearr[2]);
	if(month<=12)
	{
		if(day<=31)
		{
			if(year>1900 && year<4000)
			{
				return true;
			}
		}
	}
		return false;
}
