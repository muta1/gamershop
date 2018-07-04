pipeline {
  agent any
  stages {
    stage('First Step tests') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('See Workspace') {
      parallel {
        stage('List Docker Containers') {
          steps {
            sh 'ls $WORKSPACE'
          }
        }
        stage('teste') {
          steps {
            sh 'echo \'nada\''
          }
        }
      }
    }
    stage('List Docker Containers') {
      steps {
        sh 'docker ps -a'
      }
    }
    stage('Login DockerHub') {
      steps {
        sh 'docker login -u mutaodockerhub -p Iftm2018'
      }
    }
  }
}