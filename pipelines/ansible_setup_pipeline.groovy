pipeline {
    agent { label 'ssh-agent' }

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
                    docker exec ansible-agent bash -c '
                        ansible-playbook \
                            -i /home/ansible/work/hosts.ini \
                            /home/ansible/work/setup_test_server.yml
                    '
                """
            }
        }
    }
}
