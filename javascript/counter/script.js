let counter = 0;

document.getElementById("increment").addEventListener("click", () => {
  counter++;
  updateCounter();
});

document.getElementById("decrement").addEventListener("click", () => {
  counter--;
  updateCounter();
});

function updateCounter() {
  document.getElementById("counter-value").textContent = counter;
}
