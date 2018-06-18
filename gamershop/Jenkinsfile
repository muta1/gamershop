pipeline {
  agent any
  stages {
    stage('Maven Tests') {
      steps {
        sh 'mvn clean install'
        sh 'ls $WORKSPACE'
      }
    }
    stage('Docker Login') {
      steps {
        sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD'
      }
    }
    stage('Docker Setups') {
      parallel {
        stage('Application Docker Setup') {
          steps {
            sh '''cd $WORKSPACE/target/mutao
rm -rf $JENKINS_HOME/Dockerfiles/Tomcat/ROOT/*
mv * $JENKINS_HOME/Dockerfiles/Tomcat/ROOT/
touch $JENKINS_HOME/Dockerfiles/Tomcat/persistence.xml
touch $JENKINS_HOME/Dockerfiles/Tomcat/web.xml'''
          }
        }
        stage('DataBase Docker Setup') {
          steps {
            sh '''touch $JENKINS_HOME/Dockerfiles/Mariadb/my.cnf
touch $JENKINS_HOME/Dockerfiles/Mariadb/start.sh'''
          }
        }
      }
    }
    stage('Docker Builds') {
      parallel {
        stage('Application Docker Build') {
          steps {
            sh '''cd $JENKINS_HOME/Dockerfiles/Tomcat
docker build -t $DOCKERHUB_USERNAME/$APPLICATION_NAME  .'''
          }
        }
        stage('Database Docker Build') {
          steps {
            sh '''cd $JENKINS_HOME/Dockerfiles/Mariadb
docker build -t $DOCKERHUB_USERNAME/$DATABASE_NAME  .'''
          }
        }
      }
    }
    stage('Pushing Docker Builds') {
      parallel {
        stage('Application Docker Pushing') {
          steps {
            sh 'docker push $DOCKERHUB_USERNAME/$APPLICATION_NAME'
          }
        }
        stage('Database Docker Pushing') {
          steps {
            sh 'docker push $DOCKERHUB_USERNAME/$DATABASE_NAME'
          }
        }
      }
    }
  }
  environment {
    DOCKERHUB_USERNAME = 'mutaodockerhub'
    DATABASE_NAME = 'mariadb'
    APPLICATION_NAME = 'gamershop'
    DOCKERHUB_PASSWORD = 'Iftm2018'
  }
  post {
   failure {
     mail(to: 'italo.mutao@gmail.com', subject: "Failed Pipeline: ${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}.")
   }  
   success {
     mail(to: 'italo.mutao@gmail.com', subject: "Successed Pipeline: ${currentBuild.fullDisplayName}", body: "${env.BUILD_URL} was successefully build.")
   }
  }
}
