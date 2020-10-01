# SpringWeb
A spring boot project created using JSP, authentication and authorization is performed using spring security.
EclipseLink is used for JPA implementation.
D3 Charts is used for displaying the graphs.
Postgres Database is used in the application, scripts are provided as well.

Once the URL is launched, click on Register button to create a new user and then login using the newly created user.

User is allowed to associate a role and each role as privileges which can be associated with to a user.
User edit and deletion operation is allowed only if the user has the required privileges. This is done using spring security.

Password hashing is used to store the password.
