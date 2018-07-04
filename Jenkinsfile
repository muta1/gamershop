pipeline {
  agent any
  stages {
    stage('First Step for tests') {
      parallel {
        stage('First Step for tests') {
          steps {
            sh '''docker login -u mutaodockerhub -p iftm2018
'''
          }
        }
        stage('Docker ps -a') {
          steps {
            sh 'docker ps -a'
          }
        }
      }
    }
  }
}