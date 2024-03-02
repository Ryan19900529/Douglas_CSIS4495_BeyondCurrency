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
