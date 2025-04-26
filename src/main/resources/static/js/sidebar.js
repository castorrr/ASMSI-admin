document.addEventListener("DOMContentLoaded", function () {
  const sidebar = document.getElementById("sidebar");
  const sidebarToggle = document.getElementById("sidebarToggle");
  const menuToggle = document.getElementById("menuToggle");
  
  // Check if elements exist
  if (!sidebar) {
    console.error("Sidebar not found!");
    return;
  }
  
  // Desktop sidebar toggle functionality
  if (sidebarToggle) {
    sidebarToggle.addEventListener("click", function() {
      sidebar.classList.toggle("collapsed");
      
      // Save state to localStorage
      const isCollapsed = sidebar.classList.contains("collapsed");
      localStorage.setItem("sidebarCollapsed", isCollapsed);
    });
  }
  
  // Mobile menu toggle functionality
  if (menuToggle) {
    menuToggle.addEventListener("click", function() {
      document.body.classList.toggle("sidebar-active");
      
      // Change icon based on sidebar state
      const isSidebarActive = document.body.classList.contains("sidebar-active");
      
      if (menuToggle.querySelector("i")) {
        if (isSidebarActive) {
          menuToggle.querySelector("i").classList.remove("bi-list");
          menuToggle.querySelector("i").classList.add("bi-x");
        } else {
          menuToggle.querySelector("i").classList.remove("bi-x");
          menuToggle.querySelector("i").classList.add("bi-list");
        }
      }
    });
  }
  
  // Apply saved state from localStorage
  const savedState = localStorage.getItem("sidebarCollapsed");
  if (savedState === "true") {
    sidebar.classList.add("collapsed");
  }
  
  // Handle window resize events
  window.addEventListener("resize", function() {
    if (window.innerWidth <= 992) {
      // Mobile view
      if (document.body.classList.contains("sidebar-active")) {
        if (menuToggle && menuToggle.querySelector("i")) {
          menuToggle.querySelector("i").classList.remove("bi-list");
          menuToggle.querySelector("i").classList.add("bi-x");
        }
      }
    }
  });
  
  // Initial setup based on screen size
  if (window.innerWidth <= 992) {
    if (menuToggle && menuToggle.querySelector("i")) {
      menuToggle.querySelector("i").classList.add("bi-list");
    }
  }
});