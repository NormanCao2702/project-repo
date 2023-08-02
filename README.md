# FortisBC x SwiftPO

## Requirements for the second iteration:
1. A landing page for SwiftPO resembling FortisBC’s Connector website’s style and structure. 
2. The ability to sign up, and then login with the information provided during sign up.
3. On successful login, user is guided to the area where they will eventually place/track/edit(for next iterations) order requests.

## Requirements for the first iteration:
1. Using a User database to assist with signing up and login process
2. Using a Request database to help with storing/drafting purchase order request
logic
3. Sending user a welcome email on signing up
a. Integration of JavaEmailSender API
4. Integration of BCryptPasswordEncoder API
a. Securing the user password to store in a database
5. Purchase Order form for users to enter data fields which will be saved in the
database
6. Giving user ability to edit a draft purchase order
7. Separate draft purchase order and submitted purchase orders and show them on
user dashboard
a. Make sure that user is only able to see the forms the user has drafted or
submitted, not the form for other users
8. As long as user hasn’t logged out, user session will be active and they can see
their account


07/05/2023 - Version One(To be color coded later in the next iterations)

## Important Information:
Our group worked on two repositories for this project. The first one - “https://github.com/ShokrollahMichaelMeraj/FortisBC-SwiftPO” which has most of our work had some issues during deploying which we weren’t able to fix in time. So near the deadline, we had to make a new repository - “https://github.com/NormanCao2702/project-repo” for deployment. Most of our work is done and problems are solved on this repo. This will be our main repo from now. For our employer project, our new requirements state that we won’t need to use Azure AD for authentication with Single Sign-on and Microsoft Account Login anymore. Hence we will create the database for authentication account ourselves as well as user types. We still keep the old version of the login form; however, did more improvement on it so it work similarly to Azure AD with some new function like show password, users type and password being encrypted and sent to the database, email sent for notification.

##  Project Abstract
The SwiftPO (Swift Purchase Order) web application aims to automate the IS (information systems) Consulting Procurement workflow at FortisBC through its’ implementation on the FortisBC Connector website. The application will enable IS users to request a Purchase Order (PO) when engaging with external parties (contractors), and the IS Vendor Management Office (VMO) to respond to the request. Currently, the process involves manually filling up a total of 3 forms, which can be time-consuming and prone to errors. The existing process requires users to fill out a Purchase
Requisition form, a Procurement checklist form, and a Sourcing form. This manual dataentry process increases the chances of incomplete or inaccurate information being submitted, leading to delays and potential issues during the review and processing stages. The main purpose of SwiftPO is to automate the process of gathering necessary details, for the PO and ensuring completeness before submission to the IS Vendor Management Office team for review and processing. Our solution will streamline and enhance the efficiency of the procurement workflow.

## Links:
● Web Application: https://swiftpo.onrender.com/
● GitHub Repository: https://github.com/NormanCao2702/project-repo
● Existing systems (APIs):
● JavaxMail API:
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframe
work/mail/javamail/JavaMailSender.html
● BCryptEncoder API:
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/se
curity/crypto/bcrypt/BCryptPasswordEncoder.html

## Customer 
The customers for this web app would primarily be the IS (Information Systems) users within the company who need to engage with external parties (contractors) and request
Purchase Orders (POs). The intended recipients and utilizers of the IS PO Request web application are employees who are affiliated with FortisBC's Information Systems division. This will include IS project personnel tasked with interfacing with vendors and launching procurement procedures within the IS department. The Vendor Management Ofiice teams are also other employees within FortisBC who will be using the web application and reviewing the purchase orders sent by IS users for approval.

## Competitive Analysis
In the purchase order and workflow automation solutions market, our project faces competition from established software providers such as SAP Ariba, Coupa, Oracle
Procurement Cloud, Jaggaer, Ivalua, and Basware. While these competitors offer valuable features, our project stands out through its unique approach. Unlike a complete reimplementation of an existing product, our project combines the best elements from different solutions and enhances them with innovative features.
1) Integration with FortisBC Connector Website: By providing a landing page within the FortisBC Connector website (company intranet), your project leverages the existing infrastructure and familiarity of the employees. This integration can offer a seamless experience and better adoption among IS users.
2) Vendor Management Office (VMO) Collaboration: The project emphasizes collaboration with the VMO team, enabling them to review, process, and track the status of the PO requests efficiently. This collaboration feature ensures a streamlined workflow and better communication between IS users and the VMO team.
3) Automated Data Gathering and Validation: By automating the process of gathering details for the PO request, the project reduces manual data entry errors and ensures completeness. The system can validate the entered information and prompt users for any missing or incorrect data, leading to more accurate and complete PO requests.
4) Transparent Status Tracking: The project offers a clear and user-friendly status tracking mechanism for PO requests, allowing users to easily monitor the progress and current state of their requests. This transparency enhances visibility and accountability in the procurement process.

## User Stories
About user stories:
According to the new requirements, there is no need to connect our landing page with neither Azure AD or SAP, which also leads to changes to certain scenarios.

User Stories:
● (Iteration 1 & Iteration 2) FortisBC IS user, Roger, wants to access SwiftPO. He is able to sign in into his FortisBC account by typing his credentials in the authentication form. He will be able to view if he type in the password correctly as he wanted by using the show/hide button. After the submission, the system will check his credentials against the database. Upon successful authentication, the
user gains access to the application's features and functionalities and will be notified via email that he uses to sign up/login. If the credentials are invalid, the system should not let the user access the web application. The final goal is for the internal employee to successfully request the product of the company through our landing page. (similar to the previous iteration but not through Azure AD)
● (Iteration 2) FortisBC employee John Smith needs to sign up. He will go to the landing page and click the sign up button. The button will redirect John to the sign up form where he will need to fill in required information including email, full name, password, and the type of employee he is. After submiting, if the information is correct, John’s information will be saved into a database, his password will be encrypted, he will receive an email welcoming him to the application, and he will be taken to the login page.
● (Iteration 2) FortisBC employee Michael wants to log out of the application. He will navigate to the bottom of the page and click the logout icon. Michael will be
logged out accordingly.
● (Iteration 2) After logging in FortisBC employee Andy need to go to the submitted orders from the previous work day, He will open the side navigation and by clicking the icon at the top left of the page and will be able to see the option in the navigation bar titled “Submitted Orders”. After clicking Submitted Orders, he will be able to see all of the orders which he had submitted.
● (Iteration 2) FortisBC IS user, Greg just finish login using email and password and user type; however, he is tired of having to fill in all the same information again. The application will have the ability to automatically grab all the information he already used for login that can be used for the form and paste it to where it is.
● (Iteration 2) FortisBC IS user, Peter is filling in the forms after he logs in successfully; however, he does not have enough time to finish the order. He
cannot submit it since the form needs to be fully filled first. The application will have the “save as draft” button so that he will be able to save his forms for the next time he log in and finish the form and edit his past saved version.
● (Iteration 3) David, an IS team lead, requires a specialized piece of hardware to enhance the network infrastructure. He accesses the IS PO Request page and
selects "Electric" as the type of request. He fills in the vendor details, including the legal name, address, and contact information. As it is a new vendor, David ensures that AP is notified to set up the vendor in SAP. He attaches the quotation for the hardware and submits the request. The VMO reviews the request, approves it, and creates a purchase order in SAP. David receives a notification that his request has been processed and the vendor is being engaged.
● (Iteration 3) John is a VMO user within SwiftPO. Assuming He has logged in and credentials are correct and exist within the database. He clicks review pending purchase orders, and is taken to alist of orders pending approval. John goes through the information entered by the information system user and and comments on the fields that contain information that are not correct, he leaves comments with steps on what is wrong and whatto do, and sends back the order to the IS user which sent teh purchase order initially.
● (Iteration 3) Marie tries logging in but has forgotten her information. She clicks forgotten password in the login page and is met with a prompt asking for her email. Assuming the email exists, she receives an email with a link for resetting her password. Marries clicks the email and opens the link and is asked to give new password.
● (Iteration 3) Bobby wants to look at his profile and information. Assuming he has logged in. He clicks a profile icon which will redirect him to a page with his personal information as well as options to edit his avatar picture, email, name, user name, phone number or delete his profile.


# New functions added for higher velocity in this iteration:
Welcome email sent after signup.
● Show/hide password
● Password being encrypted and saved to the database
● Functional navigation bar with user friendly animation.
● Matching the theme of the project employer.
● Integration with JavaxMail and BCryptEncoder API through new configuration
and service classes.
● New Purchase Order feature: create new PO in the same page, auto fill email,
click question icon to get information, rich text editor-user friendly to format the
text.
● Draft: can be able to see your form that you submitted and edit.
● Submitted form: can only review your form, no editing
● Database: Able to retrieve information of the user when creating a new PO, using
Postgres SQL.
● New class, repo, controller file for forms created and saved other tables.
● Added review button, user can review the order sent to vmo.
● Overall work: Redesign everything to look more elegant and user friendly.

#For Iteration 3:
Our group will aim to implement the rest of the functionality that involves the different landing pages for IS Users and VMO Office employees. It will also involve the logic in the communication between these two kinds of users. We also need to include more information in the form but used less information as we wanted to focus on the correct logic first. In the end, we will package all information and send it to the next management to continue the procurement process.




