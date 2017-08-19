pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Building..'
		checkout scm
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
