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
      .container {
        margin: auto;
        width: 800px;
      }
      .general_info {
        margin-top: 50px;
        margin-bottom: 50px;
        text-align: right;
      }
      .customer_info {
        margin-top: 50px;
        margin-bottom: 50px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>${documentName}</h1>
      <div class="general_info">
        <p>Service country : ${serviceCountry}}</p>
        <p>Salespartner ID ${salesPartnerId}</p>
        <p>Invoice Issue Date: ${invoiceIssueDate}</p>
        <p>Venice Invoice No: ${veniceInvoiceNumber}</p>
      </div>
      <div class="customer_info">
        <p>Your reference ${aaxCustomerId}</p>
        <p>Billing Period ${billingPeriodFrom} - ${billingPeriodTo}</p>
        <p>Bill document number ${billDocNumber}</p>
        <p>Payement reference ${paymentReference}</p>
      </div>

      <table>
        <tr>
          <th>Total net</th>
          <th>Total Gross</th>
          <th>Currency</th>
        </tr>

        <tr>
          <td>${totalNet}</td>
          <td>${totalGross}</td>
          <td>${currency}</td>
        </tr>

        <tr></tr>
      </table>
    </div>
  </body>
</html>
