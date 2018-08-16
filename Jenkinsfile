pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build Quickstart') {
          steps {
            echo 'Building..'
            sh './gradle/task3/gradlew assemble -p gradle/task3/'            
          }
          post {
            success{
              archiveArtifacts artifacts: 'gradle/task3/build/libs/*.jar', fingerprint: true
            }
          }    
        }
        stage('Build Quickstart-Web') {
          steps {
            echo 'Building..'
            sh './gradle/quickstart-web/gradlew assemble -p gradle/quickstart-web/'            
          }
          post {
            success{
              archiveArtifacts artifacts: 'gradle/quickstart-web/build/libs/*.war', fingerprint: true
            }
          }     
        }
      }
    }
    stage('Test') {
      parallel {
        stage('Test Quickstart') {
          steps {
            echo 'Testing..'
            sh './gradle/task3/gradlew test jacocoTestReport -p gradle/task3/'            
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
        stage('Test Quickstart-Web') {
          steps {
            echo 'Testing..'
            sh './gradle/quickstart-web/gradlew test -p gradle/quickstart-web/'            
          }  
        }
      }
    }
    stage('Code-Quality') {
      parallel {
        stage('Code-Quality Quickstart') {
          steps {
            echo 'Inspecting code..'
            sh './gradle/task3/gradlew sonarqube -p gradle/task3/'            
          }   
        }
        stage('Code-Quality Quickstart-Web') {
          steps {
            echo 'Inspecting code..'
            sh './gradle/quickstart-web/gradlew sonarqube -p gradle/quickstart-web/'            
          }   
        }
      }
    }
    stage('Deploy') {
      parallel {
        stage('Deploy Quickstart') {
          steps {
            echo 'Deploying..'            
          }   
        }
        stage('Deploy Quickstart-Web') {
          steps {
            echo 'Deploying..'
            sh './gradle/quickstart-web/gradlew -b deploy.gradle deploy -Pdev_server=10.28.135.47 -Puser_home=/var/jenkins_home -Pwar_path=gradle/quickstart-web/build/libs -p gradle/quickstart-web/'            
          }   
        }
      }
    }
  }
}
