const API_BASE = "http://acd3de80627b04b11adaff5b3ef9e774-1146655416.ap-south-1.elb.amazonaws.com:8081";

let cart = [];
let foodItems = [];
let cartModal;

// Initialize modal on page load
document.addEventListener('DOMContentLoaded', () => {
    cartModal = new bootstrap.Modal(document.getElementById('cartModal'));
    loadFoodItems();
    updateCartCount();
});

// Fetch food items from backend
async function loadFoodItems() {
    try {
        const response = await fetch(API_BASE + '/food/items', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        });
        if (!response.ok) throw new Error('Failed to fetch');
        foodItems = await response.json();
        renderFoodCards();
    } catch (error) {
        console.error('Error loading food items:', error);
        document.getElementById('food-card-container').innerHTML = '<p class="text-danger text-center">Failed to load menu. Please try again later.</p>';
    }
}

// Render food items as cards
function renderFoodCards() {
    const container = document.getElementById('food-card-container');
    container.innerHTML = '';

    foodItems.forEach(item => {
        const vegClass = item.veg ? 'bg-success' : 'bg-danger';
        const vegLabel = item.veg ? 'Veg' : 'Non-Veg';
        const col = document.createElement('div');
        col.className = 'col-md-3 mb-4';
        col.innerHTML = `
            <div class="card shadow">
                <img src="${item.imageUrl}" class="card-img-top" alt="${item.itemName}" loading="lazy">
                <div class="card-body">
                    <h5 class="card-title">${item.itemName}</h5>
                    <p class="card-text small">${item.itemDescription}</p>
                    <span class="badge ${vegClass} mb-2">${vegLabel}</span>
                    <p class="card-text fw-bold">₹${item.price}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <input type="number" class="form-control form-control-sm quantity-input" value="1" min="1" id="qty-${item.id}">
                        <button class="btn btn-primary btn-sm" onclick="addToCart('${item.id}')">Add</button>
                    </div>
                </div>
            </div>
        `;
        container.appendChild(col);
    });
}

// Add item to cart
function addToCart(itemId) {
    const item = foodItems.find(f => f.id === itemId);
    if (!item) return;

    const qtyInput = document.getElementById(`qty-${itemId}`);
    const quantity = parseInt(qtyInput.value) || 1;

    const existing = cart.find(c => c.id === itemId);
    if (existing) {
        existing.quantity += quantity;
    } else {
        cart.push({ ...item, quantity });
    }

    updateCartCount();
    qtyInput.value = 1; // reset quantity
}

// Update cart badge count
function updateCartCount() {
    const count = cart.reduce((sum, item) => sum + item.quantity, 0);
    document.getElementById('cart-count').innerText = count;
}

// Toggle cart modal (only if cart not empty)
function toggleCart() {
    if (cart.length === 0) {
        alert('Your cart is empty. Add some items first!');
        return;
    }
    renderCartTable();
    cartModal.show();
}

// Render cart table inside modal
function renderCartTable() {
    const tbody = document.getElementById('cart-table-body');
    const totalSpan = document.getElementById('cart-total');
    if (!tbody) return;

    let html = '';
    let total = 0;
    cart.forEach(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        html += `
            <tr>
                <td>${item.itemName}</td>
                <td>₹${item.price}</td>
                <td>
                    <button class="btn btn-sm btn-outline-secondary" onclick="updateQuantity('${item.id}', -1)">-</button>
                    <span class="mx-2">${item.quantity}</span>
                    <button class="btn btn-sm btn-outline-secondary" onclick="updateQuantity('${item.id}', 1)">+</button>
                </td>
                <td>₹${itemTotal}</td>
                <td><button class="btn btn-sm btn-danger" onclick="removeFromCart('${item.id}')"><i class="bi bi-trash"></i></button></td>
            </tr>
        `;
    });
    tbody.innerHTML = html;
    totalSpan.innerText = total;
}

// Update item quantity in cart
function updateQuantity(itemId, delta) {
    const index = cart.findIndex(item => item.id === itemId);
    if (index === -1) return;

    const newQty = cart[index].quantity + delta;
    if (newQty <= 0) {
        cart.splice(index, 1);
    } else {
        cart[index].quantity = newQty;
    }

    updateCartCount();
    renderCartTable(); // refresh modal
}

// Remove item from cart
function removeFromCart(itemId) {
    cart = cart.filter(item => item.id !== itemId);
    updateCartCount();
    if (cart.length === 0) {
        cartModal.hide();
    } else {
        renderCartTable();
    }
}

// Place order
function placeOrder() {
    if (cart.length === 0) {
        alert('Cart is empty!');
        return;
    }

    // Hardcoded restaurant (you can enhance this)
    const restaurant = {
        id: 1,
        name: "Foodie Paradise",
        address: "MG Road",
        city: "Bangalore",
        restaurantDescription: "Best in town"
    };

    // Map cart items to the format expected by backend
    const foodItemsList = cart.map(item => ({
        id: item.id,
        itemName: item.itemName,
        itemDescription: item.itemDescription,
        isVeg: item.veg,
        price: item.price,
        restaurantId: item.restaurantId,
        quantity: item.quantity
    }));

    const orderPayload = {
        foodItemsList: foodItemsList,
        userId: 1, // hardcoded for now
        restaurant: restaurant
    };

    fetch(API_BASE + '/order/saveOrder', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(orderPayload)
    })
    .then(response => {
        if (!response.ok) throw new Error('Order failed');
        return response.json();
    })
    .then(data => {
        alert(`Order placed successfully! Order ID: ${data.orderId}`);
        cart = [];
        updateCartCount();
        cartModal.hide();
    })
    .catch(error => {
        console.error('Error placing order:', error);
        alert('Failed to place order. Check console.');
    });
}