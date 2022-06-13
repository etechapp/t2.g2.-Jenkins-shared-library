def call(String repoUrl){
   pipeline {
    agent any
    stages {
        stage('Compile & Build Binary') {
            parallel {
                stage('Build X') {
                     steps{
                      sh ' action build x'
                     }
                }
                stage('Build Y') {
                	steps{
                		sh 'action build y'
                	}
                      
                	}
                }
            }
        }
    }
}

 }