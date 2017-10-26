
Note: Git is a prereq

1) Download the openshift-origin-client-tools for your OS: https://github.com/openshift/origin/releases/tag/v3.6.1
1) Fork this repo: https://github.com/danmcp/openshift-jee-sample
1) git clone https://github.com/<userid\>/openshift-jee-sample.git
1) cd openshift-jee-sample
1) Login to web console
1) Create a project and give it a unique name
1) Open Metrics URL and accept the cert warnings
1) Add To Project -> Browse Catalog -> Java -> Red Hat JBoss EAP -> Red Hat JBoss EAP 7.0
   * Name: somethingcool
   * Git Repository URL: https://github.com/<userid\>/openshift-jee-sample.git
   * Blank out Git Reference and Context Dir
1) oc login
1) oc set env dc/somethingcool AUTO_DEPLOY_EXPLODED=true
1) oc get pods
1) oc rsync target/SampleApp.war/ <pod_name>:/deployments/SampleApp.war --watch
1) Browse to SampleApp/HelloWorld
1) Make a change to HelloWorld and/or HelloWorldHelper.java
1) Browse to SampleApp/HelloWorld
1) oc set env dc/somethingcool DEBUG=true
1) oc get pods
1) oc port-forward <pod_name> 8787:8787
1) oc rsync target/SampleApp.war/ <pod_name>:/deployments/SampleApp.war
1) Set a break point and browse to SampleApp/HelloWorld
1) Change danmcp in pipeline.yaml to be your github login
1) oc create -f https://raw.githubusercontent.com/<userid\>/openshift-jee-sample/master/pipeline.yaml
1) Modify Jenkinsfile inside of node(maven):

          //stage('build') {
          //  git url: "https://github.com/<userid>/openshift-jee-sample.git"
          //  openshiftBuild(buildConfig: 'somethingcool', showBuildLogs: 'true')
          //}
          stage('test') {
            git url: "https://github.com/<userid>/openshift-jee-sample.git"
            //sh "mvn verify"
            sh "mvn clean"
          }
          stage('approval') {
            input "Approve?"
          }
          stage('deploy') {
            openshiftDeploy(deploymentConfig: 'somethingcool')
          }
          stage('scale') {
            openshiftScale(deploymentConfig: 'somethingcool', replicaCount: '2')
          }
          stage('system test') {
            //sh "sleep 10"
            sh "curl http://somethingcool:8080 | grep WildFly"
          }

1) git add .
1) git commit -m 'Adding stages'
1) git push origin master