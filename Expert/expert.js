//load more work history
const loadMore = document.querySelector(".expert__section-history-more");

let currentItems = 3;
loadMore.addEventListener("click", (e) => {
  const elementList = [
    ...document.querySelectorAll(".expert__section-history-contents-li"),
  ];
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
let displayImage = document.getElementById("love__icon");
displayImage.addEventListener("click", (e) => {
  if (e.target.src.match("./img/love-circled.png")) {
    e.target.src = "./svg/love-circled-filled.svg";
  } else {
    e.target.src = "./img/love-circled.png";
  }
});
