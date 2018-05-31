$(document).ready(function(){
    $("#submit").click(function(){
        $.ajax({
        		type : "GET",
        		url : window.location + "/" + $("#id").val(),
        		success: function(data) {
        			$("#result").html(data.value + " " + data.currency);
        		}
        	});
    });
})