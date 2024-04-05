 let bell = document.getElementById("bell");
 let box = document.querySelector(".notifi-box");
 let dot = document.querySelector(".notifi-dot");
 let down = false;

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
});

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
