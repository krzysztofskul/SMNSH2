document.addEventListener('DOMContentLoaded', () => {

	//alert("test");

    const images = [
        '/pics/homepage/homepage01.jpg',
        '/pics/homepage/homepage02.jpg',
        '/pics/homepage/homepage03.jpg'
    ];

    let currentIndex = 0;
    const imageElement = document.getElementById('homepage-image');

    function changeImage() {
        currentIndex = (currentIndex + 1) % images.length;
        imageElement.style.opacity = 0; // Fade out
        
        setTimeout(() => {
            imageElement.src = images[currentIndex];
            imageElement.style.opacity = 1; // Fade in
        }, 500);
    }

    setInterval(changeImage, 4000); // Change image every 4 seconds
});