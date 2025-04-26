// Function to clear the other dropdown when one is selected
document.addEventListener('DOMContentLoaded', function () {
    const familySaintSelect = document.getElementById('familySaint');
    const schoolYearSelect = document.getElementById('schoolYear');

    // When Family Saint is selected, clear the School Year dropdown
    familySaintSelect.addEventListener('change', function () {
      if (familySaintSelect.value) {
        schoolYearSelect.value = '';  // Clear School Year
      }
    });

    // When School Year is selected, clear the Family Saint dropdown
    schoolYearSelect.addEventListener('change', function () {
      if (schoolYearSelect.value) {
        familySaintSelect.value = '';  // Clear Family Saint
      }
    });
  });