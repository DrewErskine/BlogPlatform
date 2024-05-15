document.addEventListener("DOMContentLoaded", function() {
    const postForm = document.getElementById("postForm");
    if (postForm) {
        postForm.addEventListener("submit", function(event) {
            event.preventDefault();

            // Fetch CSRF header and token from meta tags
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
            const csrfToken = getCsrfToken();

            // Collect form data
            const formData = new FormData(postForm);
            const plainFormData = Object.fromEntries(formData.entries());
            const formDataJsonString = JSON.stringify(plainFormData);

            // Send POST request with form data and CSRF token
            fetch('/api/posts', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: formDataJsonString,
                credentials: 'same-origin'
            })
            .then(handleResponse)
            .then(data => {
                alert('Post created successfully');
                window.location.href = '/blog'; // Redirect to blog page after success
            })
            .catch(handleError);
        });
    }
});

function getCsrfToken() {
    return document.querySelector('meta[name="_csrf"]').content;
}

function handleResponse(response) {
    if (!response.ok) {
        if (response.headers.get("content-type").includes("application/json")) {
            return response.json().then(data => {
                throw new Error(data.message || 'Failed to create post');
            });
        }
        throw new Error('Server error occurred. Please try again.');
    }
    return response.json();
}

function handleError(error) {
    console.error('Error:', error);
    alert('Error creating post: ' + error.message);
}
