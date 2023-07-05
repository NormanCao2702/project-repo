## Employer Project Name: 
SwiftPO x FortisBC Web Application


___

## Abstract:

The SwiftPO (Swift Purchase Order) web application aims to automate the IS (information systems) Consulting Procurement workflow at FortisBC through its’ implementation on the FortisBC Connector website. The application will enable IS users to request a Purchase Order (PO) when engaging with external parties (contractors), and the IS Vendor Management Office (VMO) to respond to the request. Currently, the process involves manually filling up a total of 3 forms, which can be time-consuming and prone to errors. The existing process requires users to fill out a Purchase Requisition form, a Procurement checklist form, and a Sourcing form. This manual data entry process increases the chances of incomplete or inaccurate information being submitted, leading to delays and potential issues during the review and processing stages.

The main purpose of SwiftPO is to automate the process of gathering necessary details, for the PO and ensuring completeness before submission to the IS Vendor Management Office team for review and processing. Our solution will streamline and enhance the efficiency of the procurement workflow. The features of the application will include a user authentication system using Azure AD to identify FortisBC IS users, while also populating the required fields in our application with the help of Microsoft Graph API. Users will be guided through a series of questions to gather all necessary details, while dynamically generating required fields. The application might need to gather necessary vendor details by integrating with SAP or SharePoint. Apart from error-checking and verifying complete information (including project details, contractor information, budget allocation, and contract duration), users and the IS VMO team will be able to track the status of the PO request throughout the workflow stages. Upon completion and approval of all details, the application will generate a Purchase Requisition in SAP for processing. As evident, the application will be used for operational purposes.

___

## Links:
- Web Application: 


- GitHub Repository: https://github.com/ShokrollahMichaelMeraj/FortisBC-SwiftPO


- Existing systems (APIs):

   - Azure API: https://learn.microsoft.com/en-us/rest/api/azure/
   - Other Possible APIs: 
        - SAP and Digital Signature API


## Features:
The main features of the IS Consulting Procurement workflow automation project include user authentication through Azure AD, dynamic form generation for efficient data collection, vendor management integration with SAP or SharePoint for streamlined vendor selection and retrieval, attachment handling for supporting documents, a request status tracking system to monitor the progress of each PO request, and notification functionality to keep stakeholders informed at each stage of the process.

### Authentication and Login via Azure

The login feature in SwiftPO provides secure access for FortisBC IS users. Users can authenticate themselves using their FortisBC-provided credentials through Azure Active Directory. This ensures authorized access to the SwiftPO application, maintaining data confidentiality and protecting against unauthorized access.


## Stories/Scenarios:

1- FortisBC IS user Roger wants to access SwiftPO. He is able to sign into their account by typing his credentials in the authentication form. After their submission, the system will check the input against Azure AD. Upon successful authentication, the user gains access to the application's features and functionalities. If the credentials are invalid, the system should not let the user access the web application. The final goal is for the internal employee can successfully request the product of the company through our landing page.


2- David, an IS team lead, requires a specialized piece of hardware to enhance the network infrastructure. He accesses the IS PO Request page and selects "Electric" as the type of request. He fills in the vendor details, including the legal name, address, and contact information. As it is a new vendor, David ensures that AP is notified to set up the vendor in SAP. He attaches the quotation for the hardware and submits the request. The VMO reviews the request, approves it, and creates a purchase order in SAP. David receives a notification that his request has been processed and the vendor is being engaged.


___
 
## Do we have a clear understanding of the problem?


### How is this problem solved currently (if at all)?
There are companies that offer automated purchase orders, however, they are not customizable to users' specifications.


### How will this project make life better? Is it educational or just for entertainment?
SwiftPO does not directly help educate anyone, nor will it be used for entertainment. The main purpose of SwiftPO is to streamline the procurement workflow for Information systems. Its main purpose will be to simplify request processes and make vendor management efficient with proper compliance and documentation.


### Who is the target audience? Who will use your app or play your game?
The intended recipients and utilizers of the IS PO Request web application are individuals who are affiliated with FortisBC's Information Systems division. This will include IS project personnel tasked with interfacing with vendors and launching procurement procedures within the IS department. 



## What is the scope of your project?


### Be honest, is the amount of work required in this proposal enough for five group members?

The amount of work required in this proposal is substantial and encompasses various aspects such as system integration, user authentication, dynamic form generation, error-checking, and workflow tracking. With such a diverse and demanding set of tasks, assigning five group members allows for efficient task allocation, collaboration, and effective management of the project's complexity.





