document.addEventListener("DOMContentLoaded", () => {
    const schoolYearForm = document.getElementById("schoolYearForm");
    const addFamilySaintForm = document.getElementById("addFamilySaintForm");
    const familySaintTableBody = document.getElementById("familySaintTableBody");
    const toastEl = document.getElementById("saveSuccessToast");

    let currentSchoolYear = "2024-2025"; // Default initial value

    // Function to load family saints for a given school year
    const loadFamilySaints = (schoolYear) => {
        fetch(`/family-saint-settings/api?schoolYear=${encodeURIComponent(schoolYear)}`)
            .then(response => response.json())
            .then(data => {
                familySaintTableBody.innerHTML = "";
                data.forEach((saint, index) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${index + 1}</td>
                        <td>${saint.schoolYear}</td>
                        <td>${saint.saintName}</td>
                    `;
                    familySaintTableBody.appendChild(row);
                });
            });
    };

    // Load saints when school year form is submitted
    schoolYearForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const input = document.getElementById("schoolYear");
        currentSchoolYear = input.value;
        loadFamilySaints(currentSchoolYear);
    });

    // Handle add family saint form submission
    addFamilySaintForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const schoolYear = document.getElementById("inputSchoolYear").value;
        const saintName = document.getElementById("inputSaintName").value;
        const csrfToken = document.querySelector('input[name="_csrf"]').value;

        fetch("/addFamilySaint", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": csrfToken
            },
            body: JSON.stringify({ schoolYear, saintName })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to save Family Saint");
            }
            return response.json();
        })
        .then(data => {
            // Reset the form
            addFamilySaintForm.reset();

            // Reload the table if the current year matches
            if (data.schoolYear === currentSchoolYear) {
                loadFamilySaints(currentSchoolYear);
            }

            // Show success toast
            const toast = new bootstrap.Toast(toastEl);
            toast.show();
        })
        .catch(error => {
            console.error("Error saving Family Saint:", error);
            alert("There was an error saving the Family Saint.");
        });
    });

    // Initial load
    loadFamilySaints(currentSchoolYear);
});
