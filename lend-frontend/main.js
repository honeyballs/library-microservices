const lendUrl = "http://localhost:8082";
const paths = ["/books", "/customers", "/lends"];
let customerData = [];
let bookData = [];
let lendData = [];

refreshTables();

document.getElementById("confirm").onclick = borrow;

function refreshTables() {
    paths.forEach(path => {
        fetch(lendUrl + path, {method: "GET", mode: "cors"})
            .then(response => response.json())
            .then(data => {
                switch (path) {
                    case "/books":
                        bookData = data;
                        populateBookTable();
                        break;
                    case "/customers":
                        customerData = data;
                        populateCustomerTable();
                        break;
                    case "/lends":
                        lendData = data;
                        populateLendTable();
                        break;
                }
            });
    });
}

function populateCustomerTable() {
    const table = document.getElementById("customer-table");
    const tbody = table.tBodies.item(0);
    tbody.innerHTML = "";
    customerData.forEach((customer, index) => {
        let row = tbody.insertRow(index);
        row.insertCell(0).innerHTML = customer.id;
        row.insertCell(1).innerHTML = customer.firstname;
        row.insertCell(2).innerHTML = customer.lastname;
    });
}

function populateBookTable() {
    const table = document.getElementById("book-table");
    const tbody = table.tBodies.item(0);
    tbody.innerHTML = "";
    bookData.forEach((book, index) => {
        let row = tbody.insertRow(index);
        row.insertCell(0).innerHTML = book.id;
        row.insertCell(1).innerHTML = book.title;
        row.insertCell(2).innerHTML = book.isbn;
        row.insertCell(3).innerHTML = book.stock
    });
}

function populateLendTable() {
    const table = document.getElementById("lend-table");
    const tbody = table.tBodies.item(0);
    tbody.innerHTML = "";
    lendData.forEach((lend, index) => {
        let row = tbody.insertRow(index);
        row.insertCell(0).innerHTML = lend.id;
        row.insertCell(1).innerHTML = lend.customerId;
        row.insertCell(2).innerHTML = lend.bookIds;
        row.insertCell(3).innerHTML = `${lend.returnDate[2]}.${lend.returnDate[1]}.${lend.returnDate[0]}`;
        row.insertCell(4).innerHTML = lend.returned;
        if (lend.returned) {
            row.insertCell(5).innerHTML = "Already returned"
        } else {
            const returnButton = document.createElement("button");
            returnButton.innerText = "Return";
            returnButton.onclick = _ => returnBooks(lend.id);
            row.insertCell(5).appendChild(returnButton);
        }
    });
}

function borrow(event) {
    event.preventDefault();
    const formData = new FormData(document.getElementById("create-form"));
    const books = formData.get("book-ids").replace(" ", "").split(",");
    const customer = formData.get("customer-id");
    fetch(`${lendUrl}/lends/lend/${customer}`, {
        method: "POST",
        mode: "cors",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(books)
    })
        .then(res => res.json())
        .then(lend => {
            lendData.push(lend);
            refreshTables();
        });
}

function returnBooks(lendId) {
    fetch(`${lendUrl}/lends/return/${lendId}`, {method: "GET", mode: "cors"})
        .then(_ => refreshTables());
}