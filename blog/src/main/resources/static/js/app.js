function getCsrfToken() {
    return document.querySelector('meta[name="_csrf"]').content;
}

document.addEventListener("DOMContentLoaded", function() {
    const postForm = document.getElementById("postForm");
    if (postForm) {
        postForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const formData = new FormData(postForm);
            const plainFormData = Object.fromEntries(formData.entries());
            const formDataJsonString = JSON.stringify(plainFormData);

            fetch('/api/posts', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: getCsrfToken()
                },
                body: formDataJsonString,
                credentials: 'same-origin'
            })
            .then(handleResponse)
            .catch(handleError);
        });
    }
});

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
