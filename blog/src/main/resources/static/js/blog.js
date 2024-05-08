// blog.js
document.addEventListener("DOMContentLoaded", function() {
    const postsContainer = document.getElementById("posts-container");
    if (postsContainer) {
        fetch('/api/posts')
            .then(response => response.json())
            .then(posts => {
                postsContainer.innerHTML = '';
                posts.forEach(post => {
                    const postElement = document.createElement("div");
                    postElement.className = 'blog-post';
                    postElement.innerHTML = `
                        <h3>${post.title}</h3>
                        <p>Author: ${post.author || 'Anonymous'}</p>
                        <p>${post.content.substring(0, 200)}...</p>
                        <a href="./post.html?id=${post.id}" class="button">Read More</a>
                    `;
                    postsContainer.appendChild(postElement);
                });
            })
            .catch(error => {
                console.error('Error loading posts:', error);
                postsContainer.innerHTML = '<p>Error loading posts.</p>';
            });
    }
});
