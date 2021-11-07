$(function () {

    function getData() {
        $.ajax({
            url: "http://localhost:8080/users",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
                table(data);
            }
        });
    }
    getData()
    let myTable = document.querySelector('#table');
    let headers = ['ID', 'Username', 'Password', 'Email', 'PhoneNumber', 'Role', 'Update', 'Delete'];

    function table(users) {

        let table = document.createElement('table');
        let headerRow = document.createElement('tr');

        headers.forEach(headerText => {
            let header = document.createElement('th');
            let textNode = document.createTextNode(headerText);
            header.appendChild(textNode);
            headerRow.appendChild(header);
        });

        table.appendChild(headerRow);

        users.forEach(user => {
            let row = document.createElement('tr');

            Object.values(user).forEach(text => {
                let cell = document.createElement('td');
                let textNode = document.createTextNode(text);
                cell.appendChild(textNode);
                row.appendChild(cell);
            })

            let cell = document.createElement('td');
            let updateBtn = document.createElement("button");
            updateBtn.setAttribute('value', "update");
            cell.appendChild(updateBtn);
            row.appendChild(cell);
            updateBtn.addEventListener("click",() => {


            });
            let cell1 = document.createElement('td');
            let deleteBtn = document.createElement("button");
            deleteBtn.setAttribute('content', "Delete");
            cell1.appendChild(deleteBtn);
            row.appendChild(cell1);
            cell1.appendChild(deleteBtn);
            row.appendChild(cell1);
            deleteBtn.addEventListener("click",() => {
                $.ajax({
                    url: "http://localhost:8080/user/"+user.id,
                    method:"DELETE",
                    dataType: "JSON",
           
                }).done (()=>{
                    table();
                });
            });
            table.appendChild(row);
        });
        myTable.appendChild(table);
    }
})
