document.addEventListener("DOMContentLoaded", function () {
  const signupContainer = document.getElementById("signup-container");
  const backToLoginBtn = document.getElementById("back-to-login");
  const requestAccountForm = document.getElementById("requestAccountForm");

  // Add animation on page load (Signup form slides in, modal expands)
  setTimeout(() => {
    signupContainer.classList.add("signup-active");
  }, 100);

  // Handle back to login animation
  if (backToLoginBtn) {
    backToLoginBtn.addEventListener("click", function (event) {
      event.preventDefault(); // Prevent immediate redirection

      signupContainer.classList.remove("signup-active");
      signupContainer.classList.add("signup-reverse"); // Add reverse animation

      setTimeout(() => {
        window.location.href = "/login"; // Redirect after animation
      }, 500);
    });
  }

  // Handle form submission
  if (requestAccountForm) {
    requestAccountForm.addEventListener("submit", function (event) {
      event.preventDefault(); // Prevent default form submission

      // Submit the form data to the backend
      const formData = new FormData(requestAccountForm);

      fetch("/request-account", {
        method: "POST",
        body: formData,
      })
        .then((response) => response.text())
        .then((data) => {
          // Show the success modal
          const successModal = new bootstrap.Modal(
            document.getElementById("successModal")
          );
          successModal.show();

          // Reset the form after submission
          requestAccountForm.reset();
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    });
  }
});

function togglePassword() {
  const passwordField = document.getElementById("password");
  const icon = document.querySelector(".toggle-password i");

  if (passwordField.type === "password") {
    passwordField.type = "text";
    icon.classList.remove("bi-eye"); // Bootstrap Icons class
    icon.classList.add("bi-eye-slash"); // Bootstrap Icons class
  } else {
    passwordField.type = "password";
    icon.classList.remove("bi-eye-slash"); // Bootstrap Icons class
    icon.classList.add("bi-eye"); // Bootstrap Icons class
  }
}
