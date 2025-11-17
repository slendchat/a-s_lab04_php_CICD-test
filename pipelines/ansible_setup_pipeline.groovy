pipeline {
    agent { label 'ssh-agent' }

    stages {
        stage('Run Ansible Setup') {
            steps {
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'ansible-private-key',
                        keyFileVariable: 'SSH_KEY'
                    )
                ]) {
                    sh """
                    docker exec ansible-agent bash -c '
                        export ANSIBLE_PRIVATE_KEY_FILE=/home/ansible/.ssh/id_ansible
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
