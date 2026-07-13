pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    stages {

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