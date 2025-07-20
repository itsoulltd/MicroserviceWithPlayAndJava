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
    
    ##Build project:
    ~>$ compile
    
    ##Test project:
    ~>$ test
    #Or
    ~>$ testOnly com.acme.SomeClassTest
    
##### Source from 