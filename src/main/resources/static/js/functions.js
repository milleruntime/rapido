// some functions
var QUERY = null;

function getQueryResult(){
    $('#query-output').val(QUERY);
}

/**
 * REST GET call for the query, stores it on a sessionStorage variable
 */
function doQuery(userName, query) {
    //var tableName = $('#query-select-tables').val();

    var url =  '/greeting?name=' + userName;
    var request = $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    request.done(function( msg ) {
        QUERY = JSON.stringify(msg);
    });

    request.fail(function( jqXHR, textStatus ) {
        $("#error").html(jqXHR.responseText).show();
        //alert( "Request failed: " + textStatus );
    });

    //return $.getJSON(url, function(data) { QUERY = JSON.stringify(data); });
}