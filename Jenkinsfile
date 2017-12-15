pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('After Deploy') {
            steps {
       			  echo "BUILD_ID is ${env.BUILD_ID}"
       			  echo "BUILD_NAME is ${env.BUILD_NAME}"
       			  echo "JENKINS_URL is ${env.JENKINS_URL}"
       			  echo "JOB_NAME is ${env.JOB_NAME}"
       		}
       	}
    }
}