pipeline {
    agent any

    tools {
        maven 'Maven-3.8'
        jdk 'JDK-17'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chromium', 'firefox', 'webkit'], description: 'Select browser to run tests')
        choice(name: 'HEADLESS', choices: ['true', 'false'], description: 'Run tests in headless mode?')
        string(name: 'THREAD_COUNT', defaultValue: '3', description: 'Number of parallel threads')
    }

    environment {
        ALLURE_RESULTS = 'target/allure-results'
        ALLURE_REPORT = 'target/allure-report'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "Checking out code from repository..."
                }
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                script {
                    echo "Cleaning previous build artifacts..."
                }
                bat 'mvn clean'
            }
        }

        stage('Install Playwright') {
            steps {
                script {
                    echo "Installing Playwright browsers..."
                }
                bat 'mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"'
            }
        }

        stage('Compile') {
            steps {
                script {
                    echo "Compiling the project..."
                }
                bat 'mvn compile'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "Running tests with browser: ${params.BROWSER}, headless: ${params.HEADLESS}"
                }
                bat """
                    mvn test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -DthreadCount=${params.THREAD_COUNT}
                """
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    echo "Generating Allure report..."
                }
                allure([
                    includeProperties: false,
                    jdk: '',
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: env.ALLURE_RESULTS]]
                ])
            }
        }
    }

    post {
        always {
            script {
                echo "Archiving test results and reports..."
            }
            // Archive test results
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'

            // Archive screenshots
            archiveArtifacts artifacts: '**/target/screenshots/**/*', allowEmptyArchive: true

            // Archive traces
            archiveArtifacts artifacts: '**/target/traces/**/*', allowEmptyArchive: true

            // Clean workspace
            cleanWs()
        }

        success {
            script {
                echo "✅ Build and tests completed successfully!"
            }
        }

        failure {
            script {
                echo "❌ Build or tests failed. Check the logs and reports for details."
            }
        }
    }
}
