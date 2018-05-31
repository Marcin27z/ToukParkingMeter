$(document).ready(function(){
    $("#submit").click(function(){
        $.post("/stop/post",
        {
          id: $("#id").val(),
        },
        function(data){
            if (data == true) {
                $("#result").html("Parking stopped");
            } else {
                $("#result").html("Failed to stop parking");
            }
            $("#id").val("");
        });
    });
})