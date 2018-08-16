pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        parallel(
          app: {
            echo 'Building..'
            sh './gradle/task3/gradlew assemble -p gradle/task3/'            
          }, 
          web: {
            sh './gradle/quickstart-web/gradlew assemble -p gradle/quickstart-web/'
          }
        )        
      }
      post {
        success{
          archiveArtifacts artifacts: 'gradle/task3/build/libs/*.jar', fingerprint: true
          archiveArtifacts artifacts: 'gradle/quickstart-web/build/libs/*.war', fingerprint: true
        }
      }
    }
    stage('Test') {
      steps {
        parallel(
          app: {
            echo 'Testing..'
            sh './gradle/task3/gradlew test jacocoTestReport -p gradle/task3/'            
          }, 
          web: {
            sh './gradle/quickstart-web/gradlew test -p gradle/quickstart-web/'
          }
        )       
      }
      post {
        success{
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
      }
    }
	  stage('CodeInspection') {
      steps {
        parallel(
          app: {
            echo 'Inspecting code..'
            sh './gradle/task3/gradlew sonarqube -p gradle/task3/'            
          }, 
          web: {
            sh './gradle/quickstart-web/gradlew sonarqube -p gradle/quickstart-web/'
          }
        )            
      }
    }
    stage('Deploy') {
      steps {
        parallel(
          app: {
            echo 'Deploying..'           
          }, 
          web: {
            sh './gradle/quickstart-web/gradlew -b deploy.gradle deploy -Pdev_server=10.28.135.47 -Puser_home=/var/jenkins_home -Pwar_path=gradle/quickstart-web/build/libs -p gradle/quickstart-web/'
          }
        )            
      }
    }
  }
}
