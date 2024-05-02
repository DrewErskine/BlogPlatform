document.addEventListener("DOMContentLoaded", function() {
    // Handle form submission
    const postForm = document.getElementById("postForm");
    if (postForm) {
        postForm.addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            const formData = new FormData(postForm);

            // Example POST request using Fetch API
            fetch('/api/posts', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                alert('Post submitted successfully!');
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Failed to submit post.');
            });
        });
    }
});
