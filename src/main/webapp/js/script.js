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


/**
 * variables for the map and dashboard drawing
 * 
 */


//set the inital map variables for full scope
var map;
var latlng = new google.maps.LatLng(0,0);
var zoom = 1;
var saveMarkers = [];


//some dashboard variables
var eventsdashboard;
var eventsdata;

var conflict = 0;
var earthquake = 0;
var epidemic = 0;
var flood = 0;
var hurricane = 0;
var tornado = 0;
var tsunami = 0;
var other = 0;


//Timeline variables

var tl;
var timeLineEventsArray = [];
var timeLineEventArray = [];
var timeLineAreaArray = [];
var resizeTimerID = null;
var eventsTlSource = new Timeline.DefaultEventSource();
var eventTlSource = new Timeline.DefaultEventSource();
var jsonEvents;
var jsonAAs;
var jsonSAs;
var jsonObj;
var jsonEventObj;
var theme;
var tempEvents = [];
var tempEvent = [];

//Messgase Variables
var msgForm;



jQuery(document).ready(function()
		{
			showStatusMessage();
			if(document.getElementById("orgname")!="undefined"&& document.getElementById("orgname")!=null)document.getElementById("orgname").focus();
		
			else if(document.getElementById("username")!="undefined"&& document.getElementById("username")!=null)document.getElementById("username").focus();
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
	
	var status=statusmsgstatus.split("A")[0];
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
    document.cookie = cookieName + "=" + escape(cookieData) + ";path=/accoord; expires=" + expireDate.toGMTString();
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

	if(!fail && document.getElementById("j_username")!=null && document.getElementById("j_username")!="undefined")
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


//Draw Event Maps
function drawEventsMap(events)
{
    var myOptions = {
  	      zoom: zoom,
  	      center: latlng,
  	      mapTypeId: google.maps.MapTypeId.TERRAIN
  	    };
  	    
  	map = new google.maps.Map(document.getElementById('map_canvas'),
  	        myOptions);
  	
	//iterate over the json
	$.each(events, function(key, event) { 
		
		//set up icons		
		
		switch (event.eventtype)
		{
		case "Conflict":
		iconPath =rootPath+"/resources/images/icons/grn-blank.png";
		conflict = conflict + 1;
  		break;
		case "Earthquake":
		iconPath =rootPath+"/resources/images/icons/dull-red-circle.png";
		earthquake = earthquake + 1;
	  	break;
		case "Epidemic":
		iconPath =rootPath+"/resources/images/icons/ylw-blank.png";
		epidemic = epidemic + 1;
	  	break;	  
		case "Flood":
		iconPath =rootPath+"/resources/images/icons/ltblu-blank.png";
		flood = flood + 1;
	  	break;	  
		case "Hurricane":
		iconPath =rootPath+"/resources/images/icons/blu-blank.png";
		hurricane = hurricane + 1;
	  	break;
		case "Tornado":
		iconPath =rootPath+"/resources/images/icons/grn-blank.png";
		tornado = tornado + 1;
	  	break;	  
		case "Tsunami":
		iconPath =rootPath+"/resources/images/icons/blu-blank.png";
		tsunami = tsunami + 1;
		break;	  
		default:
		iconPath =rootPath+"/resources/images/icons/wht-blank.png";
		other = other + 1;
		}
		
		
		var icon = new google.maps.MarkerImage(iconPath,
			new google.maps.Size(16, 16),
			new google.maps.Point(0, 0),
			new google.maps.Point(8, 16),
			new google.maps.Size(16, 16)
		//new google.maps.Point(16, 32),
		//new google.maps.Size(32, 32)
			);

		//set up markers
			  var latlng = new google.maps.LatLng(event.lat, event.lng);
			  var marker = new google.maps.Marker({
				  position: latlng, 
				  map: map, 
				  title:event.name,
				  icon: icon});
		
		//setup eventlistener	  
		google.maps.event.addListener(marker, 'click', function(){
			window.location = rootPath+ '/events/' +event.id;
			});
  	  
		//set custom properties
		marker.myCategory = event.eventtype;                                 
        marker.myDate = event.created;
        marker.myOpen = event.open;
    	//marker.myorgs = event.orgs;
		
		
			  
		//push marker into array
		
		saveMarkers.push(marker);	  	  
			  
	});
	

}


/**
 * Dashboard for events
 * 
 */
 


//dashboard

function drawSysDashboard(events) {
	
	
//Create a dashboard and data table        
eventsdashboard = new google.visualization.Dashboard(document.getElementById('dashboard')); 
eventsdata = new google.visualization.DataTable();	
orgdata = new google.visualization.DataTable();	

  
//build a datatable for the piechart
var eventpiedata = new google.visualization.DataTable();
		eventpiedata.addColumn('string', 'Event Type');
		eventpiedata.addColumn('number', 'Number');
		eventpiedata.addRows([
          ['Conflict',    conflict],
          ['Earthquake',     earthquake],
          ['Epidemic',  epidemic],
          ['Flood', flood],
          ['Hurricane', hurricane],
          ['Tornado', tornado],
          ['Tsunami', tsunami],
          ['Other',    other]
        ]);
	

// create the data table and add column headings before loop

eventsdata.addColumn('string','Name');
eventsdata.addColumn('string', 'Type of Event');
eventsdata.addColumn('date', 'Created');
eventsdata.addColumn('number', 'Assessments');
eventsdata.addColumn('number', 'Service Areas');



//count length of events array and put into rows
eventsdata.addRows(events.length);


//set the values in a loop
$.each(events, function(key, event) {
	//change json date to date object
	date = new Date(event.created);
	
	eventsdata.setCell(key, 0, event.name);
	eventsdata.setCell(key, 1, event.eventtype);
	eventsdata.setValue(key, 2, date);
	eventsdata.setValue(key, 3, event.assessmentareas.length);
	eventsdata.setValue(key, 4, event.serviceareas.length);

	
});  




// Define a category picker control for the Gender column
var categoryPicker = new google.visualization.ControlWrapper({
  'controlType': 'CategoryFilter',
  'containerId': 'CatSelect',
  'options': {
    'filterColumnLabel': 'Event Type', //this needs to be changed if you use the other data table
    'ui': {
    'labelStacking': 'horizonal',
      'allowTyping': false,
      'allowMultiple': true
    }
  }
});

//define a chart controler to handle dates
   var control = new google.visualization.ControlWrapper({
     'controlType': 'ChartRangeFilter',
     'containerId': 'control',
     'options': {
       // Filter by the date axis.
       'filterColumnIndex': 3,
       'ui': {
         'chartType': 'LineChart',
         'chartOptions': {
           'chartArea': {'width': '90%'},
           'hAxis': {'baselineColor': 'none'}
         },
         // Display a single series that shows the average of the stock open and close values.
         // Thus, this view has two columns: the date (axis) and the stock average (line series).
         'chartView': {
           'columns': [
             0, {
               'calc': 1,
               'type': 'number'
             }]
         },
         // 1 day in milliseconds = 24 * 60 * 60 * 1000 = 86,400,000
         'minRangeSize': 86400000
       }
     },
     // Initial range: 2012-02-09 to 2012-03-20.
     'state': {'range': {'start': new Date(2000, 1, 9), 'end': new Date(2012, 2, 20)}}
   });


//Define a pie chart

        
        //var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
        var piechart = new google.visualization.ChartWrapper({
      	  'chartType': 'PieChart',
      	  'containerId': 'piechart_div',
      	  'options': {
      	    'width': 450,
      	    'height': 250,
      	  	'colors':['green','red','yellow','lightblue', 'blue','green','blue', 'grey'],
      	    'title' :"Events by Type"
      	  }
      	});

// Define a bar chart
var barChart = new google.visualization.ChartWrapper({
	  'chartType': 'ColumnChart',
	  'containerId': 'barChart',
	  'options': {
	    'width': 450,
	    'height': 300,
	    'title' :"Response Events",
	    'vAxis': {title: 'Total Number', titleTextStyle: {color: 'red'}},
	    'hAxis': {title: 'Event', titleTextStyle: {color: 'blue', fontName: 'arial', fontSize: 12}},
	    'chartArea': {width:"40%",height:"50%"}
	  },
	  'view': {'columns': [0, 3, 4]}
	});

// Define a table
var eventtable = new google.visualization.ChartWrapper({
  'chartType': 'Table',
  'containerId': 'eventDataTable',
  'options': {
    'width': '400px'

  },
  'view': {'columns': [0, 1, 2]}

});



  //bind and draw
  //dashboard.bind(categoryPicker, [barChart, eventtable]);
  eventsdashboard.bind(categoryPicker, [piechart]);
  eventsdashboard.draw(eventpiedata);
  

  // When the slector is selected, togle the markers.
  google.visualization.events.addListener(categoryPicker, 'statechange', function() {

	 //clear the timeline
	eventsTlSource.clear();
	tempEvents =[];
	timeLineEventsArray =[];

 
	//hide the markers
	$.each(saveMarkers, function(index, marker) { 

		marker.setVisible(false);
		
	});    
    

    //selection array
    selections = categoryPicker.getState().selectedValues;
    
	//if selction is empty, show all markers
	if (selections == "")	{

		//rebuild the timeline with all events
			 //clear the timeline
           tempEvents =events;
		//buildEventsTimeline(events);
		
		//show the markers
		$.each(saveMarkers, function(index, marker) { 

			marker.setVisible(true);
			
		}); 
	}    
    

	//iterate over the selctions
	$.each(selections, function(index, selection) { 
	    	//iterate over the markers
			$.each(saveMarkers, function(index, marker) { 
				//  based on the alert, appears that I can access orgObject for each marker = alert(marker.myorgs);
				if (marker.myCategory == selection){
					marker.setVisible(true);
				}
			});
	    	
	    	//iterate over the tempEventsArray and only build selected 
	    	
	    	$.each(events, function(index, event) { 
	    		
				if (event.eventtype == selection){
					
					tempEvents.push(event);
				}
				
			});

	});	    

	buildEventsTimeline(tempEvents); 
    
    
  });  
  
  
  
}

/**
 * Timeline for events
 * 
 */


function buildEventsTimeline(events) {

var now = new Date();
var startDate = new Date();
var endDate = new Date();


	//loop and create each event subset
	
	$.each(events, function(index, event) { 
	
		startDate = new Date(event.created);
		startDate = new Date(startDate.getFullYear(), startDate.getMonth(),startDate.getDay());

		
		if (event.open){
			endDate = now;
		}else if (event.dateclosed == null){
			endDate = now;
		} else{
			endDate = new Date(event.dateclosed);
		}
		
		var jsonEvents = {
				"start": startDate,
				"end": endDate,
				"title":event.name,
				"instant":true,
				"description":event.description
		};

         //on this iteration, push the jsonEvents
		timeLineEventsArray.push(jsonEvents);
         
	});	

//now out of loop, add the array to the json
jsonObj = {
			"events":timeLineEventsArray
	};
    
//change the theme to add scrollbar
theme = Timeline.ClassicTheme.create(); // create the theme
            theme.autoWidth = false;

//create the timeline
var bandInfos = [
                 Timeline.createBandInfo({
                     width:          "70%", 
                     intervalUnit:   Timeline.DateTime.YEAR, 
                     intervalPixels: 75,
                     theme:          theme,
                     eventSource: 	eventsTlSource
                 }),
                 Timeline.createBandInfo({
                	 overview:       true,
                     width:          "30%", 
                     intervalUnit:   Timeline.DateTime.DECADE, 
                     intervalPixels: 150,
                     eventSource:	eventsTlSource	
                 })
               ];
bandInfos[1].syncWith = 0;
bandInfos[1].highlight = true;
tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);

eventsTlSource.loadJSON (jsonObj, ''); 
}

//##### Timeline for one event
//##############

function buildEventTimeline(event) {

	var now = new Date();
	var startDate = new Date();
	var endDate = new Date();


		//loop and create each assessment area subset
		$.each(event.assessmentareas, function(index, aa) { 
		
			startDate = new Date(aa.created);
			startDate = new Date(startDate.getFullYear(), startDate.getMonth(),startDate.getDay());

			var jsonAAs = {
					"start": startDate,
					"title":aa.name,
					"icon":rootPath+"/resources/images/icons/dull-red-circle.png",
					"description":aa.description
			};

	         //on this iteration, push the jsonEvents
			timeLineEventArray.push(jsonAAs);
	         
		});	
		//loop and create each Services area subset
		
		$.each(event.serviceareas, function(index, sa) { 
		
			startDate = new Date(sa.created);
			startDate = new Date(startDate.getFullYear(), startDate.getMonth(),startDate.getDay());

	
			
			var jsonSAs = {
					"start": startDate,
					"title":sa.name,
					"icon":rootPath+"/resources/images/icons/dull-blue-circle.png",
					"description":sa.description
			};

	         //on this iteration, push the jsonEvents
			timeLineEventArray.push(jsonSAs);
	         
		});	
	//now out of loop, add the array to the json
	jsonEventObj = {
				"events":timeLineEventArray
		};
	    
	//change the theme to add scrollbar
	theme = Timeline.ClassicTheme.create(); // create the theme
	           theme.autoWidth = false;

	//create the timeline
	var bandInfos = [
	                 Timeline.createBandInfo({
	                     width:          "70%", 
	                     intervalUnit:   Timeline.DateTime.MONTH, 
	                     intervalPixels: 75,
	                     theme:          theme,
	                     eventSource: 	eventTlSource
	                 }),
	                 Timeline.createBandInfo({
	                	 overview:       true,
	                     width:          "30%", 
	                     intervalUnit:   Timeline.DateTime.YEAR, 
	                     intervalPixels: 150,
	                     eventSource:	eventTlSource	
	                 })
	               ];
	bandInfos[1].syncWith = 0;
	bandInfos[1].highlight = true;
	tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);

	eventTlSource.loadJSON (jsonEventObj, ''); 
	}


//####### MESSAGE FUNCTIONS

function updateMessages(messages) {
	

	
	//get the message container
	messageDivElement = document.getElementById("messages");
	// add some style
  	 messageDivElement.setAttribute("style", "width:600px");
	//empty the children
	$("#messages").empty();



	var msgLength = messages.length - 1;
    //notice that we are counting backwards
    //TODO limit the msgs to 20
	for (var i = msgLength; i >= 0; i--) {

			
				date = new Date(messages[i].created);
				hour = date.getHours();
			  	min = date.getMinutes();
				sec = date.getSeconds();
				formatedDate = date.toDateString();
			
	    	
		    	//create a holder for an individual message
		    	messageHolder = document.createElement("div");
		    	messageHolder.setAttribute("id", "msgHolders");
		    	messageHolder.setAttribute("style", "border:2px solid #dcdcdc; padding:10px; margin-bottom:10px");
		    	//create and profile pic
		    	profileImg = document.createElement("img");
		    	profileImg.setAttribute("id", "img_pic_"+i);
				profileImg.setAttribute("src", rootPath+"/ajax/getProfilePic?id=" +messages[i].owner.id);
		    	//create a message span
			  	newMsgBox = document.createElement("span");
			  	newMsgBox.setAttribute("id", "msgB_"+i);
			  	newMsgBox.setAttribute("class", "msgbox");
			  	newMsgBox.setAttribute("style", "vertical-align:top");
			  	//create a footer
			  	newMsgFooter = document.createElement("div");
			  	newMsgFooter.setAttribute("id", "msgF_"+i);
			  	newMsgFooter.setAttribute("class", "msgfooter");
	
			  	//append holder to div, then image span and footer to holder
			  	messageDivElement.appendChild(messageHolder);
			  	messageHolder.appendChild(profileImg);
			  	messageHolder.appendChild(newMsgBox);
			  	messageHolder.appendChild(newMsgFooter);
	
				$("#msgB_"+i).text(messages[i].message);
				$("#msgF_"+i).text('posted by ' +messages[i].owner.username+ ' on ' + formatedDate + ' @ ' +hour+ ' hrs and '+min+ ' minutes' );
			
	
	}	

}
    			
    			
	
    			
function showMsgForm(type){
	
	
	switch (type)
	{
	case "update":
		$("#msgDiv").toggle();
	break;
	case "photo":
		$.jGrowl("Photo posting has been disabled for the demo");
	break;
	case "file":
		$.jGrowl("File posting has been disabled for the demo");
	break;
	case "customData":
	$.jGrowl("Open Data Kit Project - Under Development");
	break;	
	default:
		$("#msgDiv").toggle();
	}
	
}


