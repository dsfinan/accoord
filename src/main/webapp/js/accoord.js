


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
var dashboard;
var eventdata;

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
var resizeTimerID = null;
var bandInfos = [
                 Timeline.createBandInfo({
                     width:          "70%", 
                     intervalUnit:   Timeline.DateTime.MONTH, 
                     intervalPixels: 75
                 }),
                 Timeline.createBandInfo({
                     width:          "30%", 
                     intervalUnit:   Timeline.DateTime.YEAR, 
                     intervalPixels: 150
                 })
               ];
bandInfos[1].syncWith = 0;
bandInfos[1].highlight = true;



/**
 * MAP for events
 * 
 */

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
	$.each(json, function(key, event) { 
		
		//set up icons		
		
		switch (event.eventtype)
		{
		case "Conflict":
		iconPath =rootPath+"/resources/images/icons/grn-blank.png";
		conflict = conflict + 1;
  		break;
		case "Earthquake":
		iconPath =rootPath+"/resources/images/icons/red-blank.png";
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
			new google.maps.Size(64, 64),
			new google.maps.Point(0, 0),
			new google.maps.Point(12, 25),
			new google.maps.Size(25, 25)
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
dashboard = new google.visualization.Dashboard(document.getElementById('dashboard')); 
eventdata = new google.visualization.DataTable();	
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

eventdata.addColumn('string','Name');
eventdata.addColumn('string', 'Type of Event');
eventdata.addColumn('date', 'Created');
eventdata.addColumn('number', 'Assessments');
eventdata.addColumn('number', 'Service Areas');



//count length of events array and put into rows
eventdata.addRows(events.length);


//set the values in a loop
$.each(events, function(key, event) {
	//change json date to date object
	date = new Date(event.created);
	
	eventdata.setCell(key, 0, event.name);
	eventdata.setCell(key, 1, event.eventtype);
	eventdata.setValue(key, 2, date);
	eventdata.setValue(key, 3, event.assessmentareas.length);
	eventdata.setValue(key, 4, event.serviceareas.length);

	
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

//Define a pie chart

        
        //var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
        var piechart = new google.visualization.ChartWrapper({
      	  'chartType': 'PieChart',
      	  'containerId': 'piechart_div',
      	  'options': {
      	    'width': 450,
      	    'height': 300,
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
  dashboard.bind(categoryPicker, [piechart]);
  dashboard.draw(eventpiedata);
  

  // When the slector is selected, togle the markers.
  google.visualization.events.addListener(categoryPicker, 'statechange', function() {

    
	//hide the markers
	$.each(saveMarkers, function(index, marker) { 

		marker.setVisible(false);
		
	});    
    

    //selection array
    selections = categoryPicker.getState().selectedValues;
    
	//if selction is empty, show all markers
	if (selections == "")	{

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
	});	    
    
    
    
  });  
  
  
}

/**
 * Timeline for events
 * 
 */


function buildEventsTimeline() {

//create the timeline
tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);
	

}