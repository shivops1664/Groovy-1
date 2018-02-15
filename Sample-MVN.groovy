
node {

	stage('checkout') {
    	echo "==============checking out the code================================"
	 	checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/erramsai/Shiva_M.git']]])
		echo 'checkedout the code'
	}

	stage('clean project'){
		try{
			echo "=============================cleaning the project======================="
			executeCommand("mvn clean")	    
		   }catch(Exception e){
		    error "Error while cleaning project " + e.message
		}
			echo "=====================mvn clean complete================"	
	}
	
	stage('compile'){
	if(params.mavenCompile){
		try{
			echo "==============================compiling the project========================"
			executeCommand("mvn compile")    	
			
		}catch(Exception e){
		    error "Error while compling the project "+e.message
		}
			echo "=============================Compile success=============================="
		echo 'Shiva'
	}
	}
	
	stage("Deploy"){
		echo "========== Deploying to tomcat ==========="
		
		echo "========== Deployed to tomcat successfully ======="
		sleep time:180 , unit: 'NANOSECONDS'
	  }
	}
