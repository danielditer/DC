pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building..'
        sh './gradle/task3/gradlew assemble -p gradle/task3/'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
        sh './gradle/task3/gradlew test jacocoTestReport -p gradle/task3/'
      }
    }
	stage('CodeInspection') {
      steps {
        echo 'Inspecting code..'
        sh './gradle/task3/gradlew sonarqube -p gradle/task3/'
      }
    }
  }
  post {
    always {
            junit 'gradle/task3/build/test-results/test/*.xml'
            publishHTML(target: [allowMissing: true, 
                         alwaysLinkToLastBuild: false,  
                         keepAll: true, 
                         reportDir: 'gradle/task3/build/reports/tests/test', 
                         reportFiles: 'index.html', 
                         reportTitles: "Simple Report",
                         reportName: 'JUnit Test Reports'])
            publishHTML(target: [allowMissing: true, 
                        alwaysLinkToLastBuild: false, 
                        keepAll: true, 
                        reportDir: 'gradle/task3/build/jacocoHtml', 
                        reportFiles: 'index.html',
                        reportTitles: "SimpleCov Report", 
                        reportName: 'JaCoCo Coverage Reports'])
        }
    success {
      archiveArtifacts artifacts: 'gradle/task3/build/libs/*.jar', fingerprint: true
    }
  }
}