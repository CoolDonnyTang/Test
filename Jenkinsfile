pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            echo 'Building..'
          }
        }
        stage('') {
          steps {
            sleep 10
          }
        }
      }
    }
    stage('Test') {
      parallel {
        stage('Test') {
          steps {
            echo 'Testing..'
          }
        }
        stage('test2') {
          steps {
            junit(testResults: 'rgd', healthScaleFactor: 2)
          }
        }
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