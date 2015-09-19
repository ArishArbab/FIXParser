
function loadXMLDoc(){
    var lines = document.getElementById("fixmsg").value.replace("\n","");
    var splitLine = lines.split("\u0001");
    var rawmsg="";
    splitLine.forEach(function(entry) {
           rawmsg=rawmsg+entry+"<SOW>";
    });

    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest(); // code for IE7+, Firefox, Chrome, Opera, Safari
    }
    else{
           xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); // code for IE6, IE5
    }
    xmlhttp.onreadystatechange=function(){
        if (xmlhttp.readyState==4 && xmlhttp.status==200){
            document.getElementById("responseTable").innerHTML=xmlhttp.responseText;
            parseJson(xmlhttp.responseText);
        }
    }
    xmlhttp.open("POST","parse/"+rawmsg,true);
    xmlhttp.send();
}

function parseJson(json){
    var obj = JSON.parse(json);
    var tags = new Array();
    tags.push(["FIX Tag", "Tag Name", "Tag Value","Description"]);
    for (i = 0; i < obj.TAGS.length; i++) {
        var tag = obj.TAGS[i];
        tags.push([tag.FIX_TAG, tag.TAG_NAME, tag.TAG_VALUE,tag.DESCRIPTION]);
    }
    GenerateTable(tags)
}

function GenerateTable(tags) {
    var table = document.createElement("TABLE");
    table.border = "1";
    var columnCount = tags[0].length;
    var row = table.insertRow(-1);
    for (var i = 0; i < columnCount; i++) {
        var headerCell = document.createElement("TH");
        headerCell.innerHTML = tags[0][i];
        row.appendChild(headerCell);
    }
    for (var i = 1; i < tags.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {
            var cell = row.insertCell(-1);
            cell.innerHTML = tags[i][j];
        }
    }
    var dvTable = document.getElementById("responseTable");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}