pipeline {
    agent { label 'ansible-agent' }

    stages {
        stage('Clone Ansible Repository') {
            steps {
                sh '''
                    rm -rf ansible_repo
                    git clone https://github.com/slendchat/a-s_lab04_php_CICD-test.git ansible_repo
                '''
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                sh """
                    ansible-playbook \
                        -i ansible_repo/hosts.ini \
                        ansible_repo/setup_test_server.yml
                """
            }
        }
    }
}
