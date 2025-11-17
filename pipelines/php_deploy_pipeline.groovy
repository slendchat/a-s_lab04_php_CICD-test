pipeline {
    agent { label 'ansible-agent' }

    stages {
        stage('Clone PHP Project') {
            steps {
                sh '''
                    rm -rf project
                    git clone https://github.com/slendchat/a-s_lab04_php_CICD-test.git project
                '''
            }
        }

        stage('Deploy to Test Server') {
            steps {
                sh """
                    export PATH="/opt/venv/bin:\$PATH"
                    export ANSIBLE_HOST_KEY_CHECKING=False

                    /opt/venv/bin/ansible-playbook \\
                        -i /home/ansible/ansible/hosts.ini \\
                        /home/ansible/ansible/deploy_php_project.yml \\
                        -e "project_path=\${WORKSPACE}/project" # <-- ТЕПЕРЬ ANSİBLE ТОЧНО ЗНАЕТ, ГДЕ ИСКАТЬ!
                """
            }
        }
    }
}
