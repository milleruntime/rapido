// document on ready definitions
var USER = null;
$(document).ready(function() {
    // form submit for user data
    $( "form" ).submit(function( event ) {
        //var userData = $( this ).serializeArray();
        //USER = $( this ).serializeArray();
        var userName = $( "input[type=text][name=userName]" ).val();
        var query = $( "input[type=text][name=query]" ).val();
        console.log( "The user entered name: " + userName );
        console.log( "The user entered query: " + query );
        event.preventDefault();
        doQuery(userName, query);
    });

    // hide error span
    $('#error').hide();
});