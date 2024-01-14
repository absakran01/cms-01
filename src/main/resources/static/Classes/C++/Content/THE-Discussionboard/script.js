function sendMessage() {
    const messageInput = document.getElementById('message');
    const message = messageInput.value;
    const user = "blank"
    const course = "Cpp"

    if (message.length > 400) {
        alert('Message exceeds the character limit of 400 characters.');
        return;
    }

    if (message) {
        // Assuming you have an endpoint to save messages on the server
        fetch('/api/discussion/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: user,  // Set the username accordingly
                post: message,
                course: course,
            }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to save the message.');
                }
                return response.json();
            })
            .then(data => {
                // Do something with the response if needed
                console.log('Message saved successfully:', data);
            })
            .catch(error => {
                console.error('Error saving message:', error.message);
            });

        // Clear input field
        messageInput.value = '';
    } else {
        alert('Please enter a message.');
    }
    setTimeout(function () {
        location.reload(true); // true indicates a hard refresh, bypassing the cache
    }, 2000);
}
document.addEventListener('DOMContentLoaded', function () {
    // Assuming you have an endpoint to retrieve messages on the server
    fetch('/api/discussion/getAll?course=Cpp')
        .then(response => response.json())
        .then(data => {
            // Display the retrieved messages on the frontend
            const messageContainer = document.getElementById('messages');
            data.forEach(post => {
                const newMessage = document.createElement('div');
                newMessage.innerHTML = `<strong>${post.username}: </strong>${post.post}`;
                newMessage.style.border = '1px solid #ddd';
                newMessage.style.fontSize = '20px';
                newMessage.style.padding = '10px';
                newMessage.style.borderRadius = '5px';
                newMessage.style.marginBottom = '10px';
                messageContainer.appendChild(newMessage);
            });
        })
        .catch(error => {
            console.error('Error retrieving messages:', error.message);
        });
});
