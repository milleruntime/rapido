// document on ready definitions
var USER = null;
$(document).ready(function() {
    // form submit for user data
    $( "form" ).submit(function( event ) {
        //var userData = $( this ).serializeArray();
        //USER = $( this ).serializeArray();
        var userName = $( "input[type=text][name=userName]" ).val();
        var pass = $( "input[type=text][name=pass]" ).val();
        var endRow = $( "input[type=text][name=endRow]" ).val();
        var startRow = $( "input[type=text][name=startRow]" ).val();
        var tableName = getSelectedTable();

        //console.log( "The user name: " + userName );
        console.log( "Entered query row: " + startRow + "-" + endRow);
        console.log( "For table: " + tableName);
        event.preventDefault();
        doQuery(startRow, endRow, tableName);
    });

    // load tables into drop down
    loadTables();

    // hide error span
    $('#error').hide();
    // hide post query stuff
    $('#postQuery').hide();
    // hide querying msg
    $('#queryMsg').hide();
});