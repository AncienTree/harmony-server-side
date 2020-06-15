pipeline {
   agent any

   tools {
      maven "M3"
   }

   stages {
      stage('Build App') {
         steps {
            sh "mvn clean install -DskipTests=true"
         }
      }

      stage('Test App') {
        steps {
            sh "mvn test"
        }
      }

      stage('Code Analysis') {
        steps {
            sh "mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -DskipTests=true"
         }
      }
   }
}
