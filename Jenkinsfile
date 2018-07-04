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
    stage('List containers') {
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
        sh '''#test
#docker rm $(docker ps -a -q)'''
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
    stage('Run Containers Locally') {
      parallel {
        stage('Run Application Container') {
          steps {
            sh '''docker run -d -p 8888:8080 --name tomcat-application mutaodockerhub/tomcat
'''
          }
        }
        stage('Run Database Container') {
          steps {
            sh 'docker run -d -p 3316:3306 --name mariadb -v /home/mutao/Documents/JENKINS_HOME/database:/data mutaodockerhub/mariadb'
          }
        }
      }
    }
    stage('Complete') {
      steps {
        sh 'echo \'Application test, build and push OK\''
      }
    }
  }
}