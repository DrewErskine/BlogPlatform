function getCsrfToken() {
    const tokenElement = document.querySelector('meta[name="_csrf"]');
    return tokenElement ? tokenElement.content : null;
}

function getCsrfHeader() {
    const headerElement = document.querySelector('meta[name="_csrf_header"]');
    return headerElement ? headerElement.content : null;
}

document.addEventListener("DOMContentLoaded", function() {
    const postForm = document.getElementById("postForm");
    if (postForm) {
        postForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const formData = new FormData(postForm);
            const plainFormData = Object.fromEntries(formData.entries());
            const formDataJsonString = JSON.stringify(plainFormData);

            const csrfToken = getCsrfToken();
            const csrfHeader = getCsrfHeader();

            if (csrfToken && csrfHeader) {
                const headers = new Headers();
                headers.append('Content-Type', 'application/json');
                headers.append(csrfHeader, csrfToken);

                fetch('/api/posts', {
                    method: 'POST',
                    headers: headers,
                    body: formDataJsonString,
                    credentials: 'same-origin'
                })
                .then(handleResponse)
                .then(() => {
                    window.location.href = "/blog.html";
                })
                .catch(handleError);
            } else {
                console.error("CSRF token or header not found.");
            }
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
