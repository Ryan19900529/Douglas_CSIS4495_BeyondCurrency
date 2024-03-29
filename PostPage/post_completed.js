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
