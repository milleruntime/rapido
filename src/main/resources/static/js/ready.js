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
        var tableName = "blah"; //TODO get from select

        console.log( "The user entered name: " + userName );
        console.log( "The user entered query row: " + row );
        event.preventDefault();
        doQuery(userName, pass, row, tableName);
    });

    // hide error span
    $('#error').hide();
});