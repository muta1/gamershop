pipeline {
  agent any
  stages {
    stage('First Step tests') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Login Docker') {
      parallel {
        stage('List Docker Containers') {
          steps {
            sh 'docker ps -a'
          }
        }
        stage('Login Docker') {
          steps {
            sh 'docker login -u mutaodockerhub -p Iftm2018'
          }
        }
      }
    }
  }
}