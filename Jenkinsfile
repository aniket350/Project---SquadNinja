pipeline {
    agent any
​
    environment {
        repo_path = '$(basename $PWD)'
    }
​
    stages {
        stage('sync source code') {
            when{ branch 'v1.0.2'}
            steps {
                sh "rsync -rva ../${repo_path} ubuntu@10.20.1.105:/home/ubuntu/"
            }
        }
        stage('build') {
            when { branch 'v1.0.2' }
            steps {
                sh "ssh ubuntu@10.20.1.105 'cd ~/'${repo_path}' ; mvn clean package -DskipTests'"
            }
        }
        stage('Deploy') {
            when { branch 'v1.0.2' }
            steps {
                sh "ssh ubuntu@10.20.1.105 'cd ~/'${repo_path}' ; docker-compose up --build -d'"
            }
        }
        stage('Deployment status') {
            when { branch 'v1.0.2' }
            steps {
                sh "ssh ubuntu@10.20.1.105 'cd ~/'${repo_path}' ; sleep 30 ; docker ps'"
            }
        }
    }
}