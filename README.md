# FortisBC x SwiftPO

## Requirements for the first iteration:
1. A landing page for SwiftPO resembling FortisBC’s Connector website’s style and structure. 
2. The ability to sign up, and then login with the information provided during sign up.
3. On successful login, user is guided to the area where they will eventually place/track/edit(for next iterations) order requests.


07/05/2023 - Version One(To be color coded later in the next iterations)

## Important Information:
Our group worked on two repositories for this project. The first one - “https://github.com/ShokrollahMichaelMeraj/FortisBC-SwiftPO” which has most of our work had some issues during deploying which we weren’t able to fix in time. So near the deadline, we had to make a new repository - “https://github.com/NormanCao2702/project-repo” for deployment. We will need to fix this issue later during the next iterations.

For our employer project, our requirements state that we will need to use Azure AD for authentication with Single Sign-on and Microsoft Account Login. Hence we did not need to create the database for authentication as Azure AD account will have a user database as well as user types. We did try to work with Azure AD for this iteration but there is a lot of complications involved that we will have to figure out later. Hence fow now, we have provided a sign up functionality which allows a user to sign up and then those credentials can be used to login in our current application. The credentials are store only locally and not on a remote database as we will not need that in the future, we will be using Azure AD authentication instead which is a work for next iterations.

##  Project Abstract
The SwiftPO (Swift Purchase Order) web application aims to automate the IS
(information systems) Consulting Procurement workflow at FortisBC through its’
implementation on the FortisBC Connector website. The application will enable IS users to request a Purchase Order (PO) when engaging with external parties (contractors), and the IS Vendor Management Office (VMO) to respond to the request. Currently, the process involves manually filling up a total of 3 forms, which can be time-consuming and prone to errors. The existing process requires users to fill out a Purchase Requisition form, a Procurement checklist form, and a Sourcing form. This manual data entry process increases the chances of incomplete or inaccurate information being submitted, leading to delays and potential issues during the review and processing stages.

The main purpose of SwiftPO is to automate the process of gathering necessary
details, for the PO and ensuring completeness before submission to the IS Vendor
Management Office team for review and processing. Our solution will streamline and
enhance the efficiency of the procurement workflow. The features of the application will
include a user authentication system using Azure AD to identify FortisBC IS users, while
also populating the required fields in our application with the help of Microsoft Graph API.Users will be guided through a series of questions to gather all necessary details, while dynamically generating required fields. The application might need to gather necessary vendor details by integrating with SAP or SharePoint. Apart from error-checking and verifying complete information (including project details, contractor information, budget allocation, and contract duration), users and the IS VMO team will be able to track the status of the PO request throughout the workflow stages. Upon completion and approval of all details, the application will generate a Purchase Requisition in SAP for processing. As evident, the application will be used for operational purposes.

## Links:
- Web Application: https://swiftpo-project.onrender.com/


- GitHub Repository: https://github.com/NormanCao2702/project-repo

- Existing systems (APIs):

   - Azure API: https://learn.microsoft.com/en-us/rest/api/azure/
   - Other Possible APIs: 
        - SAP and Digital Signature API

## Customer 
The customers for this web app would primarily be the IS (Information Systems) users within the company who need to engage with external parties (contractors) and request Purchase Orders (POs). The intended recipients and utilizers of the IS PO Request web application are
individuals who are affiliated with FortisBC's Information Systems division. This will
include IS project personnel tasked with interfacing with vendors and launching
procurement procedures within the IS department. The application will also be used by IS
VMO team that will respond to the request.

## Competitive Analysis
In the purchase order and workflow automation solutions market, our project faces
competition from established software providers such as SAP Ariba, Coupa, Oracle
Procurement Cloud, Jaggaer, Ivalua, and Basware. While these competitors offer valuable
features, our project stands out through its unique approach. Unlike a complete
reimplementation of an existing product, our project combines the best elements from
different solutions and enhances them with innovative features.

1. Integration with FortisBC Connector Website: By providing a landing page within the FortisBC Connector website (company intranet), your project leverages the existing infrastructure and familiarity of the employees. This integration can offer a seamless experience and better adoption among IS users.

2. Vendor Management Office (VMO) Collaboration: The project emphasizes collaboration with the VMO team, enabling them to review, process, and track the status of the PO requests efficiently. This collaboration feature ensures a streamlined workflow and better communication between IS users and the VMO team.

3. Automated Data Gathering and Validation: By automating the process of gathering details for the PO request, the project reduces manual data entry errors and ensures completeness. The system can validate the entered information and prompt users for any missing or incorrect data, leading to more accurate and complete PO requests.

4. Transparent Status Tracking: The project offers a clear and user-friendly status tracking mechanism for PO requests, allowing users to easily monitor the progress and current state of their requests. This transparency enhances visibility and accountability in the procurement process.

## User Stories
1. (Iteration 1 & Iteration 2) FortisBC IS user, Roger, wants to access SwiftPO. He is able to sign in into his FortisBC account by typing his credentials in the authentication form. After the submission, the system will check his credentials against Azure AD’s database. Upon successful authentication, the user gains access to the application's features and functionalities. If the credentials are invalid, the system should not let the user access the web application. The final goal is for the internal employee to successfully request the product of the company through our landing page.


2. (Iteration 1 & Iteration 2) FortisBC VMO user, Michelle, logs into SwiftPO using her FortisBC VMO credentials. Upon successful login, Michelle gains access to the VMO dashboard where she can review and process pending order requests. She can track the status of each order request and make informed decisions based on project details, contractor information, and budget allocation. The streamlined login process allows Michelle to efficiently manage the procurement workflow, ensuring timely and accurate processing of order requests from IS users.


3. (Iteration 3) David, an IS team lead, requires a specialized piece of hardware to enhance the network infrastructure. He accesses the IS PO Request page and selects "Electric" as the type of request. He fills in the vendor details, including the legal name, address, and contact information. As it is a new vendor, David ensures that AP is notified to set up the vendor in SAP. He attaches the quotation for the hardware and submits the request. The VMO reviews the request, approves it, and creates a purchase order in SAP. David receives a notification that his request has been processed and the vendor is being engaged.



# information for development:
### gmails:
- username: cmptuser1@gmail.com
- password: cmpt276!

- username: cmptuser2@gmail.com
- password: cmpt276!


## setting up psql with render.
- create a psql on render 
- link it to web app
- code: psql -h "external link: everthing from dpg-example.com " -u "username" -d "databasename"
- enter password given from render database.


# roughdraft of changes for iteration 2
- changes for database to include user types.
- user is sent email when signing up
- requestor informtaion autofills
- two new forms created
- functionaility to navigation. and new animation.
- password is now hashed at sign up
- checks the password at login to see if it mathces with hashshed password


# still to do for iteration 2
- profile icon and profile page
- vmo and is user pages different pages
- dashboard shows reauests that have been made
- implementing the table repository and everything 

# APIs we have used:
- Javaxmail
- BCryptPasswordEncoder





