node {
	stage('checkout') {
    	cleanWs()
    	echo "==============checking out the code================================"
	 	checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/erramsai/Shiva_M.git']]])
		echo 'checkedout the code'
	}
	stage('CLEAN project') {
		sh 'mvn clean'
		echo "=====================mvn CLEAN complete================"	
	}
		stage('COMPILE project') {
		sh 'mvn compile'	 	    
		echo "=====================mvn COMPILE complete================"	
	}
		stage('TESTS project') {
		sh 'mvn test'
		echo "=====================mvn TEST complete================"	
	}
	stage('PACKAGE project') {
		sh 'mvn package'
		echo "=====================mvn PACKAGE complete================"	
	}
		stage('DEPLOY project') {
		sh 'mvn install'
		sleep time:60 , unit: 'NANOSECONDS'
		sh 'cp /var/lib/jenkins/workspace/Shiva_CTC/target/effective-java-examples-1.0.jar /var/lib/jenkins/workspace/DEPLOY-DIR'
		echo "=====================mvn DEPLOY complete================"	
	}
}