pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }

    stages 
    {
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
        
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Nov2022POMSeriesCode.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/sanitytest.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
		
		 
        stage("Deploy to stage"){
            steps{
                echo("deploy to stage")
            }
        }
        
        
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Nov2022POMSeriesCode.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/tesntngbuild.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
             
        
    }
}