let selectedUserId = null;

function openConfirmModal(button) {
  selectedUserId = button.getAttribute("data-user-id");
  const modal = new bootstrap.Modal(
    document.getElementById("confirmApproveModal")
  );
  modal.show();
}

function openDeleteModal(button) {
  selectedUserId = button.getAttribute("data-user-id");
  const modal = new bootstrap.Modal(
    document.getElementById("confirmDeleteModal")
  );
  modal.show();
}

function openRejectModal(button) {
  selectedUserId = button.getAttribute("data-user-id");
  const modal = new bootstrap.Modal(
    document.getElementById("confirmRejectModal")
  );
  modal.show();
}

function openEditModal(id, name, email, username, role) {
  document.getElementById("editUserId").value = id;
  document.getElementById("editName").value = name;
  document.getElementById("editEmail").value = email;
  document.getElementById("editUsername").value = username;

  const roleSelect = document.getElementById("editRole");
  [...roleSelect.options].forEach((opt) => {
    opt.selected = opt.value === role;
  });

  const editModal = new bootstrap.Modal(
    document.getElementById("editUserModal")
  );
  editModal.show();
}

document.addEventListener("DOMContentLoaded", function () {
  const confirmApproveBtn = document.getElementById("confirmApproveBtn");
  const approveSpinner = document.getElementById("loadingSpinner");

  const confirmDeleteBtn = document.getElementById("confirmDeleteBtn");
  const deleteSpinner = document.getElementById("deleteSpinner");

  const confirmRejectBtn = document.getElementById("confirmRejectBtn");
  const rejectSpinner = document.getElementById("rejectSpinner");

  if (confirmApproveBtn) {
    confirmApproveBtn.addEventListener("click", function () {
      approveSpinner.classList.remove("d-none");
      confirmApproveBtn.disabled = true;
      const form = document.getElementById(`approveForm-${selectedUserId}`);
      if (form) form.submit();
    });
  }

  if (confirmDeleteBtn) {
    confirmDeleteBtn.addEventListener("click", function () {
      deleteSpinner.classList.remove("d-none");
      confirmDeleteBtn.disabled = true;
      const form = document.getElementById(`deleteForm-${selectedUserId}`);
      if (form) form.submit();
    });
  }

  if (confirmRejectBtn) {
    confirmRejectBtn.addEventListener("click", function () {
      rejectSpinner.classList.remove("d-none");
      confirmRejectBtn.disabled = true;
      const form = document.getElementById(`rejectForm-${selectedUserId}`);
      if (form) form.submit();
    });
  }
});
