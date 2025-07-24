##### Prerequisite JDK-11, Scala & sbt
    ##Current Scala:v-3.7.1; sbt:v-1.11.3; Java:v-11.0.18
    ##PlayFramework:v-3.0.8
    ##Install JDK & Configure $PATH+=$JAVA_HOME
    ##Follow Linux or MacOS or Windows Instruction.
    
    ##Install Scala & sbt using brew:
    ~>$ brew update
    ~>$ brew install scala  #for scala based app
    ~>$ scala -version
    
    ##Install sbt: simple build tool
    ~>$ brew install sbt
    
    ##Build and run project:
    ~>$ cd ~/PROJECT-ROOT
    ~>$ sbt run
    
    ##Now the application server is running on port:8080
    Goto browser and hit http://localhost:8080
    
    ##Build project:
    ~>$ compile
    
    ##Test project:
    ~>$ test
    #Or
    ~>$ testOnly com.acme.SomeClassTest
    ##End
    
##### H2-DB initialization:

    #For simplicity we usaes jdbc rather using jpa-apis.
    #We can use H2 db has many verient (e.g. in-memory or file), we are using file base varient.
    #To initialize the databas schema we have to hit the http://localhost:8080
    
##### Postgres-DB initialization:

    #For enabling Postgres db we have to spinnup docker-engine (install Docker Desktop on Windows/Mac) 
    #and run following commends:
    ~>$ docker start
    ~>$ docker-compose -f docker-compose-postgres.yaml up -d
    
##### API Doc:
    
    API Endpoint: Get Books Count
    Method: GET
    URL: http://localhost:8080/api/v1/books/count
    ~>$ curl --location 'http://localhost:8080/api/v1/books/count'
    
    API Endpoint: Get Book Details by ID
    This endpoint retrieves detailed information about a specific book identified by its unique ID. 
    Request
    Method: GET
    URL: http://localhost:8080/api/v1/books/{id}
    ~>$ curl --location 'http://localhost:8080/api/v1/books/{:id}'
    
    API Endpoint: Retrieve Books
    This endpoint retrieves a list of books from the server. It allows pagination through the use of query parameters.
    Request
    Method: GET
    URL: http://localhost:8080/api/v1/books?page={:page}&limit={:limit}
    ~>$ curl --location 'http://localhost:8080/api/v1/books?page=1&limit=10'
    
    API Endpoint: Add Book Endpoint
    This endpoint allows users to add a new book to the system.
    HTTP Method: POST
    URL: http://localhost:8080/api/v1/books
    ~>$ curl --location 'http://localhost:8080/api/v1/books' \
        --header 'Content-Type: application/json' \
        --data '{
            "isbn" : "9783110545982",
            "title" : "Qualitative Interviews",
            "subtitle" : "Who set the mysterious fire",
            "copyrightYear" : "2025"
        }'
        
    API Endpoint: Update Book Information
    This endpoint allows you to update the details of an existing book in the system. 
    You can modify various attributes of a book such as its ISBN, title, subtitle, copyright year, and status.
    Method: PUT
    URL: http://localhost:8080/api/v1/books
    ~>$ curl --location --request PUT 'http://localhost:8080/api/v1/books' \
        --header 'Content-Type: application/json' \
        --data '{
                "id": 1,
                "isbn": "9783110545982",
                "title": "Qualitative",
                "subtitle": "Who set the mysterious fire and ice.",
                "copyrightYear": "2023",
                "status": "none"
            }'
            
    API Endpoint: Delete Book by ID
    This endpoint allows you to delete a specific book from the database using its unique identifier (ID). 
    Method: DELETE
    URL: http://localhost:8080/api/v1/books/{id}
    ~>$ curl --location --request DELETE 'http://localhost:8080/api/v1/books/{:id}'
    ##EOF   
    

###### Thanks for viewing! 