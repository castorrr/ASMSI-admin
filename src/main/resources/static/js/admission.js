function searchStudent() {
    // Get the values of first name and last name input fields
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;

    // Construct the URL with query parameters
    let url = '/students/search?';
    if (firstName) {
        url += `firstName=${firstName}&`;
    }
    if (lastName) {
        url += `lastName=${lastName}`;
    }

    // Make the AJAX request to the server
    fetch(url)
        .then(response => response.json())
        .then(data => {
            displaySearchResults(data);  // Call the function to display results
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displaySearchResults(students) {
    // If no students found, clear and exit
    if (students.length === 0) {
        alert('No students found');
        return;
    }

    // Assuming the first student is the one we want to display
    const student = students[0]; 

    // Populate the input fields with the student data
    document.querySelector('.lastName-input').value = student.lastName || '';
    document.querySelector('.firstName-input').value = student.firstName || '';
    document.querySelector('.middleName-input').value = student.middleName || '';
    document.querySelector('.suffix-input').value = student.suffix || '';
    document.querySelector('.barangay-input').value = student.brgy || '';
    document.querySelector('.municipality-input').value = student.municipality || '';
    document.querySelector('.province-input').value = student.province || '';
    document.querySelector('.birthdate-input').value = student.birthDate || '';
    document.querySelector('.birthPlace-input').value = student.birthPlace || '';
    document.querySelector('.age-input').value = student.age || '';
    document.querySelector('.examPlace-input').value = student.examPlace || '';
    document.querySelector('.idNum-input').value = student.idNumber || '';
    document.querySelector('.LRN-input').value = student.lrn || '';
    document.querySelector('.religion-input').value = student.religion || '';
    document.querySelector('.gender-input').value = student.gender || '';
    document.querySelector('.citizenship-input').value = student.citizenship || '';
    document.querySelector('.elementary-input').value = student.elemSchool || '';
    document.querySelector('.elemAddress-input').value = student.schoolAddress || '';
    document.querySelector('.father-input').value = student.fatherName || '';
    document.querySelector('.father-occupation-input').value = student.fatherOccupation || '';
    document.querySelector('.father-contact-input').value = student.fatherContact || '';
    document.querySelector('.mother-input').value = student.motherName || '';
    document.querySelector('.mother-occupation-input').value = student.motherOccupation || '';
    document.querySelector('.mother-contact-input').value = student.motherContact || '';
    document.querySelector('.guardian-input').value = student.guardianName || '';
    document.querySelector('.guardrel-input').value = student.guardianRelationship || '';
    document.querySelector('.guardian-occupation-input').value = student.guardianOccupation || '';
    document.querySelector('.guardian-contact-input').value = student.guardianContact || '';
    document.querySelector('.schoolYear-input').value = student.schoolYear || '';
    loadFamilySaintOptions(student.schoolYear);

    makeReadOnly();
    
}

function loadFamilySaintOptions(schoolYear) {
    const dropdown = document.getElementById('familySaint');
    fetch(`/family-saint-settings/api?schoolYear=${schoolYear}`)
        .then(res => res.json())
        .then(data => {
            dropdown.innerHTML = '<option selected disabled>Select Family Saint</option>';
            data.forEach(saint => {
                const option = document.createElement('option');
                option.value = saint.saintName;
                option.textContent = saint.saintName;
                dropdown.appendChild(option);
            });
        })
        .catch(err => console.error("Failed to fetch family saints", err));
}



function makeReadOnly() {
    const inputs = document.querySelectorAll('input');
    const select = document.querySelectorAll('select');
    
    inputs.forEach(input => {
        if (input.id !== 'firstName' && input.id !== 'lastName') {
            input.readOnly = true;
        }
    });

    select.forEach(selectElement => {
        // Ensure the dropdown is not made read-only
        selectElement.disabled = false;
    });
}

window.onload = function() {
    const familySaintSelect = document.getElementById("familySaint");
    const submitButton = document.querySelector(".submitbtn");

    // Initially disable the submit button
    submitButton.disabled = true;

    // Check if family saint is selected, then enable the button
    familySaintSelect.addEventListener("change", function() {
        if (familySaintSelect.value !== "") {
            submitButton.disabled = false; // Enable submit button
        } else {
            submitButton.disabled = true; // Disable submit button if no saint is selected
        }
    });
};

window.onload = function() {
    const familySaintSelect = document.getElementById("familySaint");
    const submitButton = document.querySelector(".submitbtn");
    const successModal = document.getElementById("successModal");
    const closeSuccessBtn = document.getElementById("closeSuccessBtn");

    // Initially disable the submit button
    submitButton.disabled = true;

    // Check if family saint is selected, then enable the button
    familySaintSelect.addEventListener("change", function() {
        if (familySaintSelect.value !== "") {
            submitButton.disabled = false; // Enable submit button
        } else {
            submitButton.disabled = true; // Disable submit button if no saint is selected
        }
    });

    // Display the success modal if successMessage is set in the model

    window.onload = function() {
        const toast = document.getElementById("toast");
        if (toast) {
            setTimeout(function() {
                toast.classList.remove("show");
                toast.classList.add("hide");
            }, 3000);
    
            document.getElementById("closeToast").addEventListener("click", function () {
                toast.classList.remove("show");
                toast.classList.add("hide");
            });
        }
    };
    
};


window.addEventListener('DOMContentLoaded', (event) => {
    const toast = document.getElementById('toastMessage');
    if (toast) {
      setTimeout(() => {
        toast.classList.add('hide');
      }, 2000); // Adjust the delay in milliseconds (4000ms = 4 seconds)
    }
  });

  function closeToast(button) {
    const toast = button.closest('.custom-toast');
    if (toast) {
      toast.classList.add('hide');
    }
  }
