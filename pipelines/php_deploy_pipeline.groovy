pipeline {
    agent { label 'ansible-agent' }

    stages {

        stage('Deploy to Test Server') {
            steps {
                sh """
                    export PATH="/opt/venv/bin:\$PATH"
                
                    export ANSIBLE_HOST_KEY_CHECKING=False

                    /opt/venv/bin/ansible-playbook \\
                      -i /home/ansible/ansible/hosts.ini \\
                      /home/ansible/ansible/deploy_php_project.yml
                """
            }
        }
    }
}
