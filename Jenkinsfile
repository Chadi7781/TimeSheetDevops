pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build Application.'
            }
        }
        stage('Test') {
            steps {
                echo 'Test App.'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy App.'
            }
        }

    }
    
    post{
        
        always{
            emailext body: 'Summary', subject: 'Pipline Status', to: 'chadi.troudi@esprit.tn'
        }
        
        
    }
}
