let bell = document.getElementById("bell");
let box = document.querySelector(".notifi-box");
let dot = document.querySelector(".notifi-dot");
let down = false;

let isNewNotification = $('input[name="isNewNotification"]').val();
console.log(isNewNotification);
if(isNewNotification !== 'true'){
    dot.style.display = "none";
}

bell.addEventListener("click", function () {
    if (down) {
        box.style.display = "none";
        down = false;
        // Change back to the original source when the box hides
        bell.src = "/img/bell.png";
    } else {
        box.style.display = "block";
        down = true;
        // Change the source when the box shows
        bell.src = "/img/bell-green.png";
        dot.style.display = "none";
    }

    // set isNewNotification to false
    $.ajax({
        type: "POST",
        url: "/setNotificationNotNew",
        data: {
            loginUserId: $('input[name="loginUserId"]').val()
        },
        success: function () {

        },
        error: function (xhr, status, error) {  // Callback function for error handling
            console.error("Error:", error);
        }
    })
});

$('.close-notification').click(function () {
    let button = $(this);
    let notificationId = button.closest('li').find('input[name="notification_id"]').val();

    $.ajax({
        type: "POST",
        url: "/notificationNotShow",
        data: {
            notificationId: notificationId,
        },
        success: function () {
            button.closest('li').fadeOut("fast");
        },
        error: function (xhr, status, error) {  // Callback function for error handling
            console.error("Error:", error);
        }
    })
})

// Check if there are no notifications
if ($('.notifi-item').length === 0) {
    let noNotificationsMessage = '<li class="notifi-item"><p>You have no notifications</p></li>';
    $(box).append(noNotificationsMessage);
}

// Close notification box when clicking outside of it
document.addEventListener("click", function (event) {
    if (!box.contains(event.target) && event.target !== bell) {
        box.style.display = "none";
        down = false;
        // Change back to the original source when the box hides
        bell.src = "/img/bell.png";
    }
});


// when btn-accept clicked
const modal = document.getElementById("modal");
// const openModals = document.querySelectorAll(".btn-accept");
const closeModal = document.querySelector(".modal__top-button");

// for (let i = 0; i < openModals.length; i++) {
//   openModals[i].addEventListener("click", () => {
//     modal.showModal();
//   });
// }

 $(document).ready(function() {
   $('.btn-accept').click(function() {
     let applicantElement = $(this).closest('.applicants li');
     let applicantId = applicantElement.attr('abbr');

     // Store selected applicant id in hidden form fields
     $('#selectedApplicantId').val(applicantId);
     modal.showModal();
   });
 });
closeModal.addEventListener("click", () => {
  modal.close();
});

// when btn-reject clicked
 $(document).ready(function () {
   $('.btn-reject').click(function () {
     let button = $(this);
     let applicationId = button.closest('li').find('input[name="application_id"]').val();
     let postId = $('#post_id').val();
     let newStatus = "rejected";

     $.ajax({
         type: "POST",
         url: "/rejectApplicant",
         data: {
             applicationId: applicationId,
             postId: postId,
             newStatus: newStatus
         },
         success: function () {
             button.closest('li').fadeOut();
         },
         error: function (xhr, status, error) {  // Callback function for error handling
             console.error("Error:", error);
         }
     })
   })
 })
