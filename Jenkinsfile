pipeline {
       environment
// {
// registry = "saoussenbenmohamed/saoussenbenmohamed"
// registryCredential= 'dockerHub'
// dockerImage = ''
// }
       
       agent any
       
       
stages{
       stage('Checkout GIT'){
       steps{
             echo 'Pulling...';
             git branch: 'chaditroudi',
            url : 'https://github.com/Chadi7781/TimeSheetDevops.git';
             }
         }
         
         stage("Test,Build"){
          steps{
          bat """mvn clean package -Dmaven.test.failure.ignore=true"""
          }
          }
          
          stage("Sonar"){
          steps{
          bat """mvn sonar:sonar"""
          }
          }
          
          stage("Nexus"){
          steps{
          bat """mvn clean package -Dmaven.test.failure.ignore=true deploy -DaltDeploymentRepository=deploymentRepo::default::file:/"""
          }
          }
     
   
//      stage('Building our image') {
//     steps {
//        script {
//           dockerImage= docker.build registry + ":$BUILD_NUMBER" 
//        }
//     }
//   }

//   stage('Deploy our image') {
//     steps {
//        script {
//          docker.withRegistry( '', registryCredential) {
//             dockerImage.push() 
//          }
//        } 
//     }
//   }

//   stage('Cleaning up') {
//     steps { 
//       bat "docker rmi $registry:$BUILD_NUMBER" 
//     }
//   }

    
       
       
          
        }
      post {
    always {
       mail to: 'chadi.troudi@esprit.tn',
          subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
          body: "${env.BUILD_URL} has result ${currentBuild.result}"
    }
  }
      
       }
