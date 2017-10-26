try {
   timeout(time: 20, unit: 'MINUTES') {
      node('maven') {
          stage('build') {
            git url: "https://github.com/danmcp/openshift-jee-sample.git"
            openshiftBuild(buildConfig: 'somethingcool', showBuildLogs: 'true')
          }
          stage('deploy') {
            openshiftDeploy(deploymentConfig: 'somethingcool')
          }
        }
   }
} catch (err) {
   echo "in catch block"
   echo "Caught: ${err}"
   currentBuild.result = 'FAILURE'
   throw err
}
