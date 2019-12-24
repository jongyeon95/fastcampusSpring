(async()=>{
    const url ='http://127.0.0.1:8080/resturants';
    const response= await fetch(url);
    const resturants = await response.json();
    const element = document.getElementById('app');
    element.innerHTML=`
    ${resturants.map(resturant=>`
    <p>
        ${resturant.id}
        ${resturant.name}
        ${resturants.address}
        </p>
    `).join('')}

    `;
})();