pipeline {
    agent { label 'ansible-agent' }

    stages {
        stage('Clone Application Repository') {
            steps {
                sh '''
                    rm -rf app_repo 
                    git clone https://github.com/slendchat/a-s_lab04_php_CICD-test.git app_repo
                '''
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                sh """
                    export PATH="/opt/venv/bin:\$PATH"

                    /opt/venv/bin/ansible-playbook \\
                        -i /home/ansible/ansible/hosts.ini \\
                        /home/ansible/ansible/setup_test_server.yml
                """
            }
        }
    }
}
