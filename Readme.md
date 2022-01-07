# Test-files-generator

## Reference Documentation

Project generating testing files for D-HUB application. 

The program takes invoices, trip details and payment requests csv and a mapping csv of EPR <-> EC1 IDs.
It updates the customers and sales partners IDs (EPR), replace them with EC1 IDs and create the new csv.

For the invoices, PDFs are also created using Pdf builder.
The output is located in the GENERATED_FILES folder.
### Usage
The folder with the program must be placed inside a "billrun" folder, containing the folders to process.

To launch the program click on the .bat script.

Csv should be in UTF-8.

### application.properties
* dsrc_template : the template to apply to PDFs. It must be uploaded first on Pdf builder.
* allowed_directories : names of the invoices directories to process, default is "BE, DE, IT".
* mapping_file_path : the mapping csv

