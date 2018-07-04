pipeline {
  agent any
  stages {
    stage('First Step tests') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Show Workspace') {
      parallel {
        stage('List Docker Containers') {
          steps {
            sh 'ls $WORKSPACE'
          }
        }
        stage('Show Application') {
          steps {
            sh '''cd $WORKSPACE/target/
ls
cd /home/mutao/
ls'''
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
    stage('SuperUser Test') {
      steps {
        sh 'sudo su'
      }
    }
  }
}