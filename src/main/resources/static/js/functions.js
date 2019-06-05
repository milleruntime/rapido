// load the tables into the select dropdown
var TABLES = null;

function loadTables() {
    // rest call to get table list
    var url =  '/getTables';
    var request = $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    request.done(function( msg ) {
        TABLES = msg;
        console.log("REST done and set TABLES.tables.length = " + TABLES.tables.length);
        $.each(TABLES.tables, function (tableName, id) {
            console.log("Setting select option tableId:" + id + " to " + tableName);
            var newOption = '<option value="'+tableName+'">' + tableName + '</option>';
            $('#tableNameSelect').append(newOption);
        });
        $("#error").html("").hide();
        $("#loadingTables").hide();
    });

    request.fail(function( jqXHR, textStatus ) {
        $("#error").append("Error: " + jqXHR.responseText + "<br/>").show();
    });
}

// get the selected table
function getSelectedTable() {
    var selectedTable = $('#tableNameSelect').val();
    console.log("User selected " + selectedTable);
    return selectedTable;
}


// some Query functions
var QUERY = null;

function getQueryResult() {
    $('#query-output').val(JSON.stringify(QUERY.results));
}

function showQueryMsg() {
    $('#queryMsg').show();
}

/**
 * REST GET call for the query, stores it on a sessionStorage variable
 */
function doQuery(startRow, endRow, tableName) {
    var url =  '/query?&startRow=' + startRow + '&endRow=' + endRow + '&tableName=' + tableName;
    var request = $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    request.done(function( msg ) {
        //QUERY = JSON.stringify(msg);
        QUERY = msg;
        $("#error").html("").hide();
        $('#queryExecuted').html(url);
        $("#postQuery").show();
        $('#queryMsg').hide();
    });

    request.fail(function( jqXHR, textStatus ) {
        $("#error").append("Error: " + jqXHR.responseText + "<br/>").show();
    });
}

/**
 * REST GET call to count the number of rows in a table
 */
function countRows(tableName) {
    // var row = null;  //TODO get row

    var url =  '/countRows?&tableName=' + tableName;
    var request = $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });

    request.done(function( msg ) {
        QUERY = JSON.stringify(msg);
        $("#error").html("").hide();
        $("#postQuery").show();
    });

    request.fail(function( jqXHR, textStatus ) {
        $("#error").append("Error: " + jqXHR.responseText + "<br/>").show();
    });
}