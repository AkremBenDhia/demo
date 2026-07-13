pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/AkremBenDhia/demo.git'
            }
        }

        stage('Build Spring Boot') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t demo-springboot .'
            }
        }

        stage('Docker Clean Old Containers') {
            steps {
                bat '''
                docker compose down
                docker rm -f jjenkins-app-1 jjenkins-postgres-1 2>NUL || exit 0
                '''
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                bat '''
                docker compose up -d
                '''
            }
        }
    }
}