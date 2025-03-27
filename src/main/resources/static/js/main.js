// Initialize tooltips
document.addEventListener('DOMContentLoaded', function() {
    // Enable Bootstrap tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Confirm before delete
    const deleteButtons = document.querySelectorAll('.delete-btn, .btn-outline-danger');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to delete this book?')) {
                e.preventDefault();
            }
        });
    });

    // Image preview for book covers
    const imageUrlInput = document.getElementById('imageUrl');
    if (imageUrlInput) {
        imageUrlInput.addEventListener('change', function() {
            const preview = document.getElementById('coverPreview');
            if (preview && this.value) {
                preview.src = this.value;
            }
        });
    }
});