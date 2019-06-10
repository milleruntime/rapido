// document on ready definitions

$(document).ready(function() {
    // clear out global JS vars
    var QUERY = null;
    var TABLES = null;

    // form submit for user data
    $( "form" ).submit(function( event ) {
        event.preventDefault();
        doQuery();
    });

    testConnection();

    // hide error span
    $('#error').hide();
    // hide post query stuff
    $('#postQuery').hide();
    // hide querying msg
    $('#queryMsg').hide();
});