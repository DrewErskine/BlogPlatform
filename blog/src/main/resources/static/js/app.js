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
            .then(response => {
                if (response.ok) {
                  return response.json();
                } else {
                  throw new Error('Network response was not ok.');
                }
              })
              
        });
    }
});
