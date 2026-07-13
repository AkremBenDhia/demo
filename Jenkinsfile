pipeline {
    agent any

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

        stage('Docker Compose') {
            steps {
                bat 'docker compose up -d'
            }
        }
    }
}