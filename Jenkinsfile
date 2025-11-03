pipeline {
    agent { label 'php-agent' }

    stages {
        stage('Install Dependencies') {
            steps {
                // Project preparation (install dependencies if needed)
                echo 'Preparing project...'
                sh '''
                  set -e
                  composer install --no-interaction --prefer-dist
                '''
            }
        }

        stage('Test') {
            steps {
                // Running tests
                echo 'Running tests...'
                sh 'vendor/bin/phpunit --configuration phpunit.xml'
            }
        }
    }

    post {
        always { echo 'Pipeline completed.' }
        success { echo 'All stages completed successfully!' }
        failure { echo 'Errors detected in the pipeline.' }
    }
}
