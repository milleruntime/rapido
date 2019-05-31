// some functions
var QUERY = null;

function getQueryResult(){
    $('#query-output').val(QUERY);
}

/**
 * REST GET call for the query, stores it on a sessionStorage variable
 */
function doQuery(userName, pass, row, tableName) {
    //var tableName = $('#query-select-tables').val();

    var url =  '/query?userName=' + userName
        + '&password=' + pass
        + '&row=' + row
        + '&tableName=' + tableName;
    var request = $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    request.done(function( msg ) {
        QUERY = JSON.stringify(msg);
        $("#error").html("").hide();
    });

    request.fail(function( jqXHR, textStatus ) {
        $("#error").append("Error: " + jqXHR.responseText + "<br/>").show();
        //alert( "Request failed: " + textStatus );
    });

    //return $.getJSON(url, function(data) { QUERY = JSON.stringify(data); });
}