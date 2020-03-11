const customerUrl = "http://localhost:8081/customers";
let customerData = [];

fetchAllCustomers();
document.getElementById("confirm").onclick = createCustomer;

function fetchAllCustomers() {
    fetch(customerUrl, {method: "GET", mode: "cors"})
        .then(response => response.json())
        .then(json => {
            customerData = json;
            populateTable(customerData);
        });
}

function populateTable(customerData) {
    const table = document.getElementById("customer-table");
    const tbody = table.tBodies.item(0);
    tbody.innerHTML = "";
    customerData.forEach((customer, index) => {
        let row = tbody.insertRow(index);
        row.insertCell(0).innerHTML = customer.id;
        row.insertCell(1).innerHTML = customer.firstname;
        row.insertCell(2).innerHTML = customer.lastname;
        row.insertCell(3).innerHTML = customer.address;
        row.insertCell(4).innerHTML = `${customer.birthday[2]}.${customer.birthday[1]}.${customer.birthday[0]}`;
    });
}

function createCustomer(event) {
    event.preventDefault();
    const formData = new FormData(document.getElementById("create-form"));
    const customer = {};
    for (let key of formData.keys()) {
        customer[key] = formData.get(key);
    }
    console.log(customer);
    fetch(customerUrl, {
        method: "POST",
        mode: "cors",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(customer)
    })
        .then(res => res.json())
        .then(customer => {
            customerData.push(customer);
            populateTable(customerData);
        });
}