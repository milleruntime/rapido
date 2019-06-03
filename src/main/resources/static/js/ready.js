// document on ready definitions
var USER = null;
$(document).ready(function() {
    // form submit for user data
    $( "form" ).submit(function( event ) {
        //var userData = $( this ).serializeArray();
        //USER = $( this ).serializeArray();
        var userName = $( "input[type=text][name=userName]" ).val();
        var pass = $( "input[type=text][name=pass]" ).val();
        var row = $( "input[type=text][name=row]" ).val();
        var tableName = getSelectedTable();

        console.log( "The user name: " + userName );
        console.log( "Entered query row: " + row );
        console.log( "For table: " + tableName );
        event.preventDefault();
        doQuery(userName, pass, row, tableName);
    });

    // load tables into drop down
    loadTables();

    // hide error span
    $('#error').hide();
});