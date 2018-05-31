$(document).ready(function(){
    $("#submit").click(function(){
        $.post("/start/post",
        {
          id: $("#id").val(),
          driverType: $("#driver_type").val()
        },
        function(data){
            if (data == true) {
                $("#result").html("Parking started");
            } else {
                $("#result").html("Failed to start parking");
            }
            $("#id").val("");
            $("#driver_type").val("REGULAR");
        });

    });
})