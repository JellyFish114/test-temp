<html lang="en">
<head>
    <title>Test</title>
    <style>
        h1 {
            text-align: center;
        }
        table {
            margin: auto;
            width: 80%;
            border: 1px solid black;
        }
    </style>

</head>
<body>
<h1>${DocumentName}</h1>
<div>
    <p>Service country : ${.vars["Service Country"]}</p>
    <p>Sales partner ID: ${.vars["Sales Partner ID"]}</p>
    <p>Invoice Issue Date: ${.vars["Invoice Issue Date"]}</p>
    <p>Venice Invoice No: ${.vars["Venice Invoice No"]}</p>
    <p>Your reference: ${.vars["AAX Customer ID"]}</p>
    <p>Billing Period: ${.vars["Billing Period from"]} - ${.vars["Billing Period to"]}</p>
    <p>Bill document number: ${.vars["Bill document number"]}</p>
    <p>Payment reference: ${.vars["Payment Reference"]}</p>

    <table>
        <tr>
            <th>Total net</th>
            <th>Total Gross</th>
            <th>Currency</th>
        </tr>

        <tr>
            <td>${.vars["Total Net"]}</td>
            <td>${.vars["Total Gross"]}</td>
            <td>${.vars["Currency"]}</td>
        </tr>

    </table>
</div>
</body>
</html>
