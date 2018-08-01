pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building..'
        sh '''
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
        sh './gradle/task3/gradlew clean build -p gradle/task3/'
      }
    }
    }
	post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/reports/**/*.xml'
        }
  }
}
