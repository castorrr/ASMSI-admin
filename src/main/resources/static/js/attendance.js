async function searchStudent() {
    const id = document.getElementById("search-id").value;
    const response = await fetch(`/api/attendance/student/${id}`);

    if (response.ok) {
        const student = await response.json();

        document.getElementById("student-info-card").classList.remove("d-none");
        document.getElementById("student-photo").src = student.photoUrl;
        document.getElementById("lrn").textContent = student.lrn;
        document.getElementById("id-number").textContent = student.idNumber;
        document.getElementById("name").textContent = student.name;
        document.getElementById("campus").textContent = student.campus;
        document.getElementById("family-saint").textContent = student.familySaint;
        document.getElementById("batch-number").textContent = student.batchNumber;
    } else {
        alert("Student not found.");
    }
}

async function submitAttendance() {
    const idNumber = document.getElementById("id-number").textContent;
    const date = document.querySelector(".attendance-date-input").value;
    const status = document.querySelector(".attendance-status-input").value;
    const remarks = document.querySelector(".remarks-input").value;

    const payload = { idNumber, date, status, remarks };

    const res = await fetch("/api/attendance/submit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
    });

    if (res.ok) {
        alert("Attendance submitted.");
    } else {
        alert("Error submitting attendance.");
    }
}
