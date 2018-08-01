pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building..'
        sh '''
        chmod +x gradle/task3/gradlew 
        ./gradle/task3/gradlew clean assemble -p gradle/task3/
        '''
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
        sh './gradle/task3/gradlew clean test -p gradle/task3/'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying....'
        sh './gradle/task3/gradlew clean build'
      }
    }
  }
}
