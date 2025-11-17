pipeline {
    agent { label 'ssh-agent' }

    stages {
        stage('Run Ansible Setup') {
            steps {
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'ssh-key-agent',
                        keyFileVariable: 'SSH_KEY',
                        usernameVariable: 'SSH_USER'
                    )
                ]) {
                    sh """
                    docker exec ansible-agent bash -c '
                        export ANSIBLE_PRIVATE_KEY_FILE=$SSH_KEY
                        ansible-playbook \
                            -i /home/ansible/work/hosts.ini \
                            /home/ansible/work/setup_test_server.yml
                    '
                    """
                }
            }
        }
    }
}
