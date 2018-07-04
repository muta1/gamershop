pipeline {
  agent any
  stages {
    stage('First Step for tests') {
      parallel {
        stage('First Step for tests') {
          steps {
            sh '''docker ps -a
'''
          }
        }
        stage('Docker ps -a') {
          steps {
            sh 'docker login -u mutaodockerhub -p Iftm2018'
          }
        }
      }
    }
  }
}