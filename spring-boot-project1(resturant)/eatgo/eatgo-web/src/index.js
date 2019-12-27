(async()=>{
    const url ='http://127.0.0.1:8080/restaurants';
    const response= await fetch(url);
    const restaurants = await response.json();
    const element = document.getElementById('app');
    element.innerHTML=`
    ${restaurants.map(restaurant=>`
    <p>
        ${restaurant.id}
        ${restaurant.name}
        ${restaurants.address}
        </p>
    `).join('')}

    `;
})();