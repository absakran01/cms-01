document.getElementById('save-changes').addEventListener('click', function() {
  // Get values from form inputs

  var currentEmail = document.getElementById('current-email').value;
  var newEmail = document.getElementById('new-email').value;
  
  var currentPassword = document.getElementById('current-password').value;
  var newPassword = document.getElementById('new-password').value;

  // Simulate saving changes (in a real scenario, this would involve a server request)
  // For demonstration purposes, just display a success message
  var statusMessage = document.getElementById('status-message');
  statusMessage.textContent = 'Changes saved successfully!';

  // Clear the status message after a few seconds
  setTimeout(function() {
    statusMessage.textContent = '';
  }, 3000);
});

document.getElementById('delete-account').addEventListener('click', function() {
  // Add logic to delete the account (in a real scenario, this would involve a server request)
  // display a confirmation message [For demonstration purposes only!!!]
  if (confirm('Are you sure you want to delete your account?')) {
    alert('Account deleted successfully!');
    // Add code here to redirect or perform additional actions after account deletion
  }
});


const dropdownBtn = document.getElementById("btn");
const dropdownMenu = document.getElementById("dropdown");
const toggleArrow = document.getElementById("arrow");

// Toggle dropdown function
const toggleDropdown = function () {
  dropdownMenu.classList.toggle("show");
  toggleArrow.classList.toggle("arrow");
};

// Toggle dropdown open/close when dropdown button is clicked
dropdownBtn.addEventListener("click", function (e) {
  e.stopPropagation();
  toggleDropdown();
});

// Close dropdown when dom element is clicked
document.documentElement.addEventListener("click", function () {
  if (dropdownMenu.classList.contains("show")) {
    toggleDropdown();
  }
});

function Darkmode() {
  var element = document.body;
  element.classList.toggle("dark-mode");
}
