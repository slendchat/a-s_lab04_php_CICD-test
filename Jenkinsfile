pipeline {
    agent {
        label 'php-agent'
    }

    stages {
        stage('Install Dependencies') {
            steps {
                echo 'Preparing project...'
                script {
                    if (isUnix()) {
                        sh 'php -v || true'
                        sh 'composer --version || true'
                        sh 'composer install --no-interaction --prefer-dist || true'
                    } else {
                        bat 'php -v || exit /b 0'
                        bat 'composer --version || exit /b 0'
                        bat 'composer install --no-interaction --prefer-dist || exit /b 0'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                script {
                    if (isUnix()) {
                        sh 'vendor/bin/phpunit --configuration phpunit.xml'
                    } else {
                        bat 'vendor\\bin\\phpunit --configuration phpunit.xml'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'All stages completed successfully!'
        }
        failure {
            echo 'Errors detected in the pipeline.'
        }
    }
}

