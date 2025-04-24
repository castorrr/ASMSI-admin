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
    const addCredentialButton = document.querySelector('#addCredentialBtn');
    
    inputs.forEach(input => {
        // Exclude the search input fields by their IDs
        // Also, ensure modal input fields are excluded by checking for modal-specific class or ID
        if (input.id !== 'firstName' && input.id !== 'lastName' && 
            !input.closest('.modal') && input !== addCredentialButton) {
            input.readOnly = true;
        }
    });

    select.forEach(selectElement => {
        // Ensure the dropdown is not made read-only
        selectElement.disabled = false;
    });

    // Ensure "Add Credential" button is not disabled
    addCredentialButton.disabled = false;
}


    let credentialCount = 0; // Keeps track of the number of credentials added

    // Function to show the modal to enter the credential name
    function showModal() {
        const modal = new bootstrap.Modal(document.getElementById('credentialModal'));
        modal.show();
    }

    // Function to add a new row to the credentials table
    function addCredentialRow() {
        const credentialName = document.getElementById('credentialNameInput').value;

        // If no name is provided, stop the function and show an alert
        if (!credentialName) {
            alert("Credential name is required.");
            return;
        }

        credentialCount++;

        // Close the modal after adding the row
        const modal = bootstrap.Modal.getInstance(document.getElementById('credentialModal'));
        modal.hide();

        const tableBody = document.getElementById('credentials-table-body');
        const newRow = document.createElement('tr');

        // Create new row content with the user-provided credential name
        newRow.innerHTML = `
            <td>${credentialName}</td>
            <td><input type="radio" name="requirement${credentialCount}" value="orgCopy" class="org-copy"></td>
            <td><input type="radio" name="requirement${credentialCount}" value="photocopy" class="photocopy"></td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="deleteCredential(${credentialCount})">Delete</button>
            </td>
        `;

        // Append the new row to the table body
        tableBody.appendChild(newRow);

        // Clear the input field for the next use
        document.getElementById('credentialNameInput').value = '';
    }

    // Function to delete a credential
    function deleteCredential(id) {
        // Logic for deleting the credential
        const row = document.querySelector(`#credentials-table-body tr:nth-child(${id})`);
        if (row) {
            row.remove();
        }
        alert('Deleted credential ' + id);
    }

    

