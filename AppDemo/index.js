$(document).ready(function () {
  var serverUrl = "http://localhost:2300/api/v1/voucher-code";
  $("#voucher-code").click(function (e) {
    e.preventDefault();
    var phoneNumber = $("#phone-number").val().trim();
    if (phoneNumber.length != 10) {
      alert("Invalid phone number. Please try again! \nExample: 0979999999");
    } else {
      $.ajax({
        url: serverUrl,
        type: "GET",
        crossDomain: true,
        contentType: "application/json",
        data: {
          "phone-number": phoneNumber,
        },
        success: function (response) {
          if (response.code == "-1") {
            alert("Unexpected Error. Please try later!");
          } else {
            alert("Your prepaid data code: " + response.code);
          }
        },
        error: function (error) {
          alert("Unexpected Error. Please try later!");
        },
      });
    }
  });

  $("#all-voucher-code").click(function (e) {
    e.preventDefault();
    var phoneNumber = $("#phone-number").val().trim();
    if (phoneNumber.length !== 10) {
      alert("Invalid phone number. Please try again! \nExample: 0979999999");
    } else {
      $.ajax({
        url: serverUrl + "/verify",
        type: "GET",
        crossDomain: true,
        contentType: "application/json",
        data: {
          "phone-number": phoneNumber,
        },
        success: function (response) {
          var otpInput = prompt(
            "This is a simple simulator for OTP code (the OTP code will be sent to device via phone-number in real scenario). Your OTP is: " +
              response.data.otpResponse.otpCode +
              "\nPlease input your OTP"
          );
          verifyOTP(
            serverUrl,
            phoneNumber,
            response.data.otpResponse.authId,
            otpInput
          );
        },
        error: function (error) {
          console.log(error);
        },
      });
    }
  });
});

function verifyOTP(serverUrl, phoneNumber, authId, otpCode) {
  $.ajax({
    url: serverUrl + "/voucher-codes",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify({
      phoneNumber: phoneNumber,
      otpResponse: {
        otpCode: otpCode,
        authId: authId,
        currentDateTime: null,
      },
    }),
    success: function (data) {
      var html = "";
      if (data.length == 0) {
        $("#table-code").html(html);
        $("#table-code").hide();
        alert("Your phone number or OTP is invalid");
        return;
      } else {
        html +=
          '<table class="table">' +
          "<thead>" +
          "<tr>" +
          "<th>No.</th>" +
          "<th>Prepaid data voucher code</th>" +
          "</tr>" +
          "</thead>" +
          "<tbody>";
        for (var i = 0; i < data.length; i++) {
          html +=
            "<tr>" +
            '<th scope="row">' +
            (i + 1) +
            "</th>" +
            "<td>" +
            data[i].code +
            "</td>" +
            "</tr>";
        }
        html += "</tbody></table>";
        $("#table-code").html(html);
        $("#table-code").show();
      }
    },
    error: function (error) {
      console.log(error);
    },
  });
}
