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
        
        
                
        stage('Regression Automation Test in QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/albinmathewQA/Aug2024Series.git'
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
        
        
                
        stage('Regression Automation Test in Stage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/albinmathewQA/Aug2024Series.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/tesntngbuild.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports for stage') {
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