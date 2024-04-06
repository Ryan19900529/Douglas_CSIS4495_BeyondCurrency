//load more work history
const loadMore = document.querySelector(".expert__section-history-more");
const historyItems = document.querySelectorAll(".expert__section-history-contents-li");

let currentItems = 3;
if (historyItems.length <= currentItems) {
  loadMore.style.display = "none";
}

loadMore.addEventListener("click", (e) => {
  const elementList = [...historyItems];
  e.target.classList.add("show-loader");

  for (let i = currentItems; i < currentItems + 3; i++) {
    e.target.classList.remove("show-loader");
    if (elementList[i]) {
      elementList[i].style.display = "block";
    }
  }
  currentItems += 3;

  if (currentItems >= elementList.length) {
    e.target.style.display = "none";
  }
});

//show contact info
const modal = document.getElementById("modal");
const openModal = document.getElementById("contactInfo");
const closeModal = document.querySelector(".modal__top-button");

openModal.addEventListener("click", () => {
  modal.showModal();
});

closeModal.addEventListener("click", () => {
  modal.close();
});

//toggle like image
$(document).ready(function() {
  $("#love__icon").on("click", function(e) {
    let userId = $('input[name="userId"]').val();
    let savedTalentId = $('input[name="talentId"]').val();

    if ($(this).attr("src").includes("/img/love-circled.png")) {
      $.ajax({
        type: "POST",
        url: "/addSavedTalent",
        data: {
          userId: userId,
          savedTalentId: savedTalentId,
        },
        success: function() {
          $(e.target).attr("src", "/svg/love-circled-filled.svg");
        },
        error: function(xhr, status, error) {
          console.error("Error:", error);
        },
      });
    } else {
      $.ajax({
        type: "POST",
        url: "/deleteSavedTalent",
        data: {
          userId: userId,
          savedTalentId: savedTalentId,
        },
        success: function() {
          $(e.target).attr("src", "/img/love-circled.png");
        },
        error: function(xhr, status, error) {
          console.error("Error:", error);
        },
      });
    }
  });
});