document.addEventListener("DOMContentLoaded", function() {
    const postForm = document.getElementById("postForm");
    if (postForm) {
        postForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const formData = new FormData(postForm);
            
            // Optionally add a CSRF token if needed
            formData.append('_csrf', document.querySelector('input[name="_csrf"]').value);

            fetch('/api/posts', {
                method: 'POST',
                body: formData,
                credentials: 'same-origin' // Ensures cookies are sent with the request if necessary
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    // First check if the response is JSON
                    if (response.headers.get("content-type").includes("application/json")) {
                        return response.json().then(data => {
                            throw new Error(data.message || 'Failed to create post');
                        });
                    } else {
                        throw new Error('Server error occurred. Please try again.');
                    }
                }
            })
            .then(data => {
                console.log('Post created:', data);
                // Use actual data to redirect
                if (data.id) { // Assuming 'id' is the property name in the response JSON
                    window.location.href = '/post/' + data.id;
                } else {
                    throw new Error('Post ID was not returned.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error creating post: ' + error.message); // Consider replacing alerts with in-page error messages
            });
        });
    }
});
