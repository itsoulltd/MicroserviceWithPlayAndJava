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
    
###### Thanks for viewing! 