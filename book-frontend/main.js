const bookUrl = "http://localhost:8080/books";
let bookData = [];

fetchAllBooks();
document.getElementById("confirm").onclick = createBook;

function fetchAllBooks() {
    fetch(bookUrl, {method: "GET", mode: "cors"})
        .then(response => response.json())
        .then(json => {
            bookData = json;
            populateTable(bookData);
        });
}

function populateTable(bookData) {
    const table = document.getElementById("book-table");
    const tbody = table.tBodies.item(0);
    tbody.innerHTML = "";
    bookData.forEach((book, index) => {
        let row = tbody.insertRow(index);
        row.insertCell(0).innerHTML = book.id;
        row.insertCell(1).innerHTML = book.title;
        row.insertCell(2).innerHTML = book.author;
        row.insertCell(3).innerHTML = book.year;
        row.insertCell(4).innerHTML = book.isbn;
        row.insertCell(5).innerHTML = book.stock;
    });
}

function createBook(event) {
    event.preventDefault();
    const formData = new FormData(document.getElementById("create-form"));
    const book = {};
    for (let key of formData.keys()) {
        key === "copies" ? book["stock"] = formData.get(key) : book[key] = formData.get(key);
    }
    fetch(bookUrl, {
        method: "POST",
        mode: "cors",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(book)
    })
        .then(res => res.json())
        .then(book => {
            bookData.push(book);
            populateTable(bookData);
        });
}