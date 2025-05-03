function searchStudent() {
    const firstName = document.getElementById('firstName')?.value;
    const lastName = document.getElementById('lastName')?.value;

    let url = '/students/search?';
    if (firstName) url += `firstName=${firstName}&`;
    if (lastName) url += `lastName=${lastName}`;

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error(`HTTP ${response.status}`);
            return response.json();
        })
        .then(data => {
            displaySearchResults(data);
        })
        .catch(error => {
            console.error('Fetch error:', error);
            alert("Failed to fetch data.");
        });
}

function displaySearchResults(students) {
    if (!students || students.length === 0) {
        alert('No students found');
        return;
    }

    const student = students[0];

    const set = (selector, value) => {
        const el = document.querySelector(selector);
        if (el) el.value = value || '';
    };

    // Step 1
    set('.lastName-input', student.lastName);
    set('.firstName-input', student.firstName);
    set('.middleName-input', student.middleName);
    set('.suffix-input', student.suffix);
    set('.barangay-input', student.brgy);
    set('.municipality-input', student.municipality);
    set('.province-input', student.province);
    set('.birthdate-input', student.birthDate);
    set('.birthPlace-input', student.birthPlace);
    set('.age-input', student.age);
    set('.examPlace-input', student.examPlace);
    set('.idNum-input', student.idNumber);
    set('.LRN-input', student.lrn);

    // Step 2
    set('.religion-input', student.religion);
    set('.gender-input', student.gender);
    set('.citizenship-input', student.citizenship);
    set('.elementary-input', student.elemSchool);
    set('.elemAddress-input', student.schoolAddress);
    set('.father-input', student.fatherName);
    set('.father-occupation-input', student.fatherOccupation);
    set('.father-contact-input', student.fatherContact);
    set('.mother-input', student.motherName);
    set('.mother-occupation-input', student.motherOccupation);
    set('.mother-contact-input', student.motherContact);
    set('.guardian-input', student.guardianName);
    set('.guardrel-input', student.guardianRelationship);
    set('.guardian-occupation-input', student.guardianOccupation);
    set('.guardian-contact-input', student.guardianContact);
    set('.schoolYear-input', student.schoolYear);

    loadFamilySaintOptions(student.schoolYear);
    makeReadOnly();
}

function loadFamilySaintOptions(schoolYear) {
    const dropdown = document.getElementById('familySaint');
    if (!dropdown) return;

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
    const selects = document.querySelectorAll('select');

    inputs.forEach(input => {
        if (input.id !== 'firstName' && input.id !== 'lastName') {
            input.readOnly = true;
        }
    });

    selects.forEach(select => {
        select.disabled = false;
    });
}

function closeToast(button) {
    const toast = button.closest('.custom-toast');
    if (toast) {
        toast.classList.add('hide');
    }
}

// ✅ Single safe DOM loaded block
window.addEventListener('DOMContentLoaded', function () {
    const familySaintSelect = document.getElementById("familySaint");
    const submitButton = document.querySelector(".submitbtn");
    const toast = document.getElementById("toastMessage");
    const closeToastBtn = document.getElementById("closeToast");

    // Disable submit initially
    if (submitButton) {
        submitButton.disabled = true;
    }

    // Enable/disable submit based on family saint selection
    if (familySaintSelect && submitButton) {
        familySaintSelect.addEventListener("change", function () {
            submitButton.disabled = familySaintSelect.value === "";
        });
    }

    // Handle toast auto-close
    if (toast) {
        setTimeout(() => toast.classList.add('hide'), 3000);

        if (closeToastBtn) {
            closeToastBtn.addEventListener("click", () => {
                toast.classList.add('hide');
            });
        }
    }
});
