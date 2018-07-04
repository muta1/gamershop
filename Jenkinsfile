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
    stage('') {
      steps {
        sh 'docker ps -a'
      }
    }
    stage('Login DockerHub') {
      steps {
        sh 'docker login -u mutaodockerhub -p Iftm2018'
      }
    }
    stage('Preparing Environment') {
      steps {
        sh '''cd $WORKSPACE/target/
rm -rf /home/mutao/Documents/JENKINS_HOME/Dockerfiles/Tomcat/ROOT/*
mv * /home/mutao/Documents/JENKINS_HOME/Dockerfiles/Tomcat/ROOT/'''
      }
    }
    stage('Docker Remove (ALL) Containers') {
      steps {
        sh 'docker rm $(docker ps -a -q)'
      }
    }
    stage('Build Containers') {
      parallel {
        stage('Build Application') {
          steps {
            sh '''echo \'Building application container\'
docker build -t mutaodockerhub/tomcat /home/mutao/Documents/JENKINS_HOME/Dockerfiles/Tomcat/'''
          }
        }
        stage('Build Database') {
          steps {
            sh '''echo \'Building DataBase (Mariadb) container\'
docker build -t mutaodockerhub/mariadb /home/mutao/Documents/JENKINS_HOME/Dockerfiles/Mariadb/'''
          }
        }
      }
    }
    stage('Check Containers') {
      steps {
        sh 'docker ps -a'
      }
    }
    stage('Dockerhub') {
      parallel {
        stage('Push Application') {
          steps {
            sh 'docker push mutaodockerhub/tomcat'
          }
        }
        stage('Push Database') {
          steps {
            sh 'docker push mutaodockerhub/mariadb'
          }
        }
      }
    }
    stage('Success Mail') {
      steps {
        mail(subject: 'Deployment JENKINS', body: 'Sucesso Deploy', from: 'italo1577@gmail.com', to: 'italo1577@gmail.com')
      }
    }
  }
}