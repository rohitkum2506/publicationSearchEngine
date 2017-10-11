pipeline {
    agent any 

    stages {
        stage('Build') { 
            steps { 
                sh 'make noTest' 
            }
        }
        stage('Test'){
            steps {
                sh 'make noTest'
            }
        }
    }
}
