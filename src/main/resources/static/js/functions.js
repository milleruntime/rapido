// load the tables into the select dropdown
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

// test connection
function testConnection() {
    var request = $.ajax({
        type: "GET",
        url: "/test"
    });
    request.done(function( msg ) {
        // load tables into drop down
        if (msg) {
            $('#connectionWarning').hide();
            $('#main').show();
            loadTables();
        } else {
            $('#main').hide();
        }
    });
    request.fail(function( jqXHR, textStatus ) {
        $('#connectionWarning').show();
    });
}

// get the selected table
function getSelectedTable() {
    var selectedTable = $('#tableNameSelect').val();
    console.log("User selected " + selectedTable);
    return selectedTable;
}


// some Query functions
function getQueryResult() {
    $('#query-output').val(JSON.stringify(QUERY.results));
}

function showQueryMsg() {
    $('#queryMsg').show();
}

/**
 * REST GET call for the query, stores it on a sessionStorage variable
 */
function doQuery() {
    var endRow = $( "input[type=text][name=endRow]" ).val();
    var startRow = $( "input[type=text][name=startRow]" ).val();
    var term = $( "input[type=text][name=term]" ).val();
    var tableName = getSelectedTable();

    console.log( "Entered query row: " + startRow + "-" + endRow);
    console.log( "For table: " + tableName);

    var url =  '/query?&startRow=' + startRow + '&endRow=' + endRow + '&tableName=' + tableName + "&term=" + term;
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