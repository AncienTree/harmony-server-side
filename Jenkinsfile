pipeline {
   agent any

   tools {
      maven "M3"
   }

   stages {
      stage('Build App') {
         steps {
            dir('harmony-schedule'){
                sh "mvn clean install -DskipTests=true"
            }
         }
      }

      stage('Test App') {
        steps {
            dir('harmony-schedule'){
                sh "mvn test"
            }
        }
      }

      stage('Code Analysis') {
        steps {
        dir('harmony-schedule'){
            sh "mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -DskipTests=true"
            }
         }
      }
   }
}
