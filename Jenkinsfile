pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...'
                git branch: 'chaditroudi', url: 'https://github.com/Chadi7781/TimeSheetDevops'
            }
        }
        
         stage('Build Project ') {
       
       steps{
           echo "Building ..."
           bat 'mvn package '
       }
    }
    
     stage('JUnit  Test ') {
       
       steps{
           echo "Test ..."
           bat 'mvn test '
       }
    }
    
        stage('Sonar ') {
       
       steps{
           echo "Analyzing quality code -sonar"
           bat 'mvn sonar:sonar '
       }
    }
    
    
      stage('Nexus ') {
       
       steps{
           echo "delivrable artefacts."
           bat 'mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet -Dversion=2.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/  -Dfile=target/Timesheet-2.0.jar '
       }
    }
    
    
       
    }
    
    
     post {  
       
      failure {
        
mail bcc: '',          body: "${env.BUILD_URL} has result ${currentBuild.result}"
, cc: '', from: '', replyTo: '', subject: "Status of pipeline: ${currentBuild.fullDisplayName}"
, to: 'troudishedy6@gmail.comm'
     } 
     
     }
     
}

