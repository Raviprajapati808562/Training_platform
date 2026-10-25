pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Spring Boot') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t training-platform:latest .'
            }
        }

        stage('Stop Existing Container') {
            steps {
                bat 'docker rm -f training-platform 2>NUL'
            }
        }

        stage('Run Docker Container') {
            steps {
                bat 'docker run -d --name training-platform -p 8080:9090 training-platform:latest'
            }
        }
    }

    post {
        success {
            echo 'Build and Docker deployment completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the Console Output.'
        }
    }
}