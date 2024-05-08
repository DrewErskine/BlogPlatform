document.addEventListener("DOMContentLoaded", function() {
  const postForm = document.getElementById("postForm");
  if (postForm) {
      postForm.addEventListener("submit", function(event) {
          event.preventDefault();
          const formData = new FormData(postForm);

          fetch('/api/posts', {
              method: 'POST',
              body: formData
          })
          .then(response => {
              if (response.ok) {
                  return response.json();
              } else {
                  throw new Error('Failed to create post');
              }
          })
          .then(data => {
              console.log('Post created:', data);
              window.location.href = './blogForm'; // Redirect to another page
          })
          .catch(error => {
              console.error('Error:', error);
              alert('Error creating post: ' + error.message);
          });
      });
  }
});
