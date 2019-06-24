 # Ultimate Fitness 

**Ultimate Fitness** is a web application that helps users tracking their fitness progress and tries to keep them motivated. 
 


## Work distribution

The backend, frontend and layout tasks were not categorically divided so that all team members could gain practical experience in all three areas. Most of the work was done in teams of two, however every member implemented at least one part more or less alone.

Team Members | Work distribution
------------ | -----------------
Lukas Reitbauer | Layout (Bootstrap), messenger, excercises, points and picture exchange system
Janik Regula | Layout (Bootstrap), forum, Login/Logout, Registration, Spring Security, Edit User/User Points.
Maximilian Schwinger | Layout (Bootstrap), forum, file up/download, Debugging, Documentation
Cynthia Zeya | Layout (Bootstrap),log, points and picture exchange system, dashboard userprofil


## Setup
 
This short guide explains how to run the Webapplication using the Eclipse IDE.
1. Download the project from the master branch of this [githubrepository.](https://github.com/LukasR056/TestUltimateFitness)
2. Clone or download and unzip the project into your workspace and import it in Eclipse.
3. Adapt the settings in db.properties file (JavaRessources/src/db.properties) in order to match your database.
4. Verify, that the jpa properties in the dispatcherservlet match your needs. Either use "create", "create-drop" or "update" when starting the project initially. 
5. Adapt the Java Build Path to your workspace default JRE. 
6. Ensure, that your Targeted Runtime is Apache Tomcat 9.0.
7. Start the web application using either Google Chrome or Firefox. 
8. In order to populate the database, either create new users or use the "fillUsers" method in the browser URL before you log in.
9.Log in as admin with username: admin password: password 
10.Log is as user with username: user password: password
11.Log is as coach with ursername: MaxSng password: password
12.Repeat step 8 with "fillPics" and "fillEx" for pictures and exercises **after** you have logged in. 
13.For uploading pictures as an administrator in the pictures tab, press the edit_picture button and use the attached pictures in swenga_pictures.rar
14. Getting the picture out of a package need a long time because of the comparison of the pics. So it takes some time until pic-packs can be bought. Otherwise an error page will appear.
