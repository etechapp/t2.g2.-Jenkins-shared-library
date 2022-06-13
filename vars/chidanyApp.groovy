def call(String repoUrl){
  pipeline {
    agent any
    stages {
        stage ('1-Git-Clone') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHubCredentials', url: 'https://github.com/etechapp/parallel-mulitbranch-pipeline.git']]])
            } 
        }
        stage ('2-Parallel-Jobs') {
            parallel {
                stage ('sub-job-1') {
                    steps {
                        sh 'lscpu'
                        sh 'uptime'
                    }
                }
                stage ('sub-job-2') {
                    steps {
                        sh 'pwd'
                        sh 'ps -ef'
                    }
                }
                stage ('sub-job-3'){
                    steps{
                        sh 'free -g'
                        sh 'bash /var/lib/jenkins/workspace/parallel-pipeline/os-jenkins-check.sh'
                    }
                }
            }
        }
        stage ('3-Deployment to Production') {
            when {
                branch 'develop'
            }
            steps {
                sh 'echo "Building artifacts from develop branch"'
                sh 'echo "Deploying to main branch for production environment"'
            }
        }
    }
}
 }