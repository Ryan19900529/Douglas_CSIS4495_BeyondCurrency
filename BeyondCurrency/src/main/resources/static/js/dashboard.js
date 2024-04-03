let swiper = new Swiper(".mySwiper", {
  slidesPerView: 5,
  spaceBetween: 50,
  slidesPerGroup: 5,
  loop: false,
  centerSlide: "true",
  fade: "true",
  grabCursor: "true",
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
    dynamicBullets: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});

// Add an event listener to detect changes in the select element
document.getElementById("filter").addEventListener("change", function() {
  // Get the selected option value
  let selectedOption = this.value;

  let cards = document.querySelectorAll(".swiper-slide.main__card.your_post");

  for (let i = 0; i < cards.length; i++) {
    let card = cards[i];
    let status = card.getAttribute("abbr");

    if (selectedOption === "all") {
      card.classList.remove("hidden");
    } else if (selectedOption === "open" && status === "open") {
      card.classList.remove("hidden");
    } else if (selectedOption === "filled" && status === "filled") {
      card.classList.remove("hidden");
    }else if (selectedOption === "closed" && status === "closed") {
      card.classList.remove("hidden");
    } else {
      card.classList.add("hidden");
    }
  }
});
