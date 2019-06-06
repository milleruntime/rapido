// document on ready definitions

$(document).ready(function() {
    // form submit for user data
    $( "form" ).submit(function( event ) {
        event.preventDefault();
        doQuery();
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