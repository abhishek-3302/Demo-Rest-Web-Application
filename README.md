# Demo-Rest-Web-Application

#List of Users
#GET(http://localhost:8080/Users)


#List a specific User based on Id
#GET(http://localhost:8080/Users/5f022aa3aec46b6b79688800)


#Created user with Id
#POST(http://localhost:8080/Users)

body-
{
    "firstname": "Sam",
    "lastname": "Wells",
    "email": "Sam@gmail.com",
    "password": "Sam@123"
}

#Update User based on Id
#PUT(http://localhost:8080/Users/5f022aa3aec46b6b79688800)


#Delete User based on Id
#DELETE(http://localhost:8080/Users/5f022b23aec46b6b79688801)


#Search based on name
#GET(http://localhost:8080/Users/search?name=Eon)

#Send Mail
#POST(http://localhost:8080/sendMail)
body-
{
    "to":"ab.7892649170@gmail.com",
    "subject":"Test Subject",
    "text":"Test Mail text1"
}

#Upload File
#POST(http://localhost:8080/uploadFile)
body-
key-file
value-select files
