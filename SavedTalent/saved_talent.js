var swiper = new Swiper(".mySwiper", {
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

  // breakpoints: {
  //   0: {
  //     slidesPerView: 1,
  //   },
  //   520: {
  //     slidesPerView: 2,
  //   },
  //   1100: {
  //     slidesPerView: 3,
  //   },
  //   1300: {
  //     slidesPerView: 4,
  //   },
  //   1625: {
  //     slidesPerView: 5,
  //   },
  // },
});

var bell = document.getElementById("bell");
var box = document.querySelector(".notifi-box");
var dot = document.querySelector(".notifi-dot");
var down = false;

bell.addEventListener("click", function () {
  if (down) {
    box.style.display = "none";
    down = false;
    // Change back to the original source when the box hides
    bell.src = "./img/bell.png";
  } else {
    box.style.display = "block";
    down = true;
    // Change the source when the box shows
    bell.src = "./img/bell-green.png";
    dot.style.display = "none";
  }
});

// Close notification box when clicking outside of it
document.addEventListener("click", function (event) {
  if (!box.contains(event.target) && event.target !== bell) {
    box.style.display = "none";
    down = false;
    // Change back to the original source when the box hides
    bell.src = "./img/bell.png";
  }
});

//toggle like image
let displayImages = document.querySelectorAll(".people__card__saved-icon");
displayImages.forEach((displayImage) => {
  displayImage.addEventListener("click", (e) => {
    if (e.target.src.includes("/img/heart.png")) {
      e.target.src = "./img/heart-filled.png";
    } else {
      e.target.src = "./img/heart.png";
    }
  });
});
