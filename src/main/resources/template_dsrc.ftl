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
        <p>Service country : ${serviceCountry}</p>
        <p>Salespartner ID ${salesPartnerId}</p>
        <p>Invoice Issue Date: ${Invoice Issue Date}</p>
        <p>Venice Invoice No: ${Venice Invoice No}</p>
      </div>
      <div class="customer_info">
        <p>Your reference ${aaxCustomerId}</p>
        <p>Billing Period ${billingPeriodFrom} - ${billingPeriodTo}</p>
        <p>Bill document number ${Bill document number}</p>
        <p>Payement reference ${Payment Reference}</p>
      </div>

      <table>
        <tr>
          <th>Total net</th>
          <th>Total Gross</th>
          <th>Currency</th>
        </tr>

        <tr>
          <td>${Total Net}</td>
          <td>${Total Gross}</td>
          <td>${Cyrrency}</td>
        </tr>

        <tr></tr>
      </table>
    </div>
  </body>
</html>
