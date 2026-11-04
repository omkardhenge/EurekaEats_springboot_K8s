const API_BASE = "http://localhost:8081";

let reviews = [];

document.addEventListener("DOMContentLoaded", () => {
    loadReviews();
});

async function loadReviews() {

    const params = new URLSearchParams(window.location.search);
    const foodId = params.get("foodId");

    document.getElementById("food-title").innerText =
        "Food Reviews";

    try {

        const response = await fetch(
            `${API_BASE}/order/reviews/${foodId}`
        );

        if (!response.ok) {
            throw new Error("API Error");
        }

        reviews = await response.json();

        renderReviews(reviews);

    } catch (error) {

        console.error(error);

        document.getElementById("review-card-container").innerHTML =
        `
        <div class="text-center text-danger">
            Failed to load reviews.
        </div>
        `;
    }
}

function renderReviews(data) {

    const container = document.getElementById("review-card-container");
    container.innerHTML = "";

    data.forEach(review => {

        let stars = "";

        for (let i = 0; i < 5; i++) {
            stars += i < review.rating ? "⭐" : "☆";
        }

        container.innerHTML += `
        <div class="col-lg-3 col-md-4 col-sm-6 mb-4">

            <div class="card h-100 shadow-sm">

                <div class="card-body d-flex flex-column">

                    <div class="fw-bold text-primary mb-2">
                        ${review.userName}
                    </div>

                    <div class="text-muted mb-2">
                        ${review.foodItem}
                    </div>

                    <div class="mb-2 text-warning fs-5">
                        ${stars}
                    </div>

                    <p class="text-secondary flex-grow-1">
                        ${review.comment}
                    </p>

                </div>

            </div>

        </div>
        `;
    });
}