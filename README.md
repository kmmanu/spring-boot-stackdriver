#Demo app to publish spring boot actuator metrics to GCP stackdriver

###Setup environment
* Install and start the Cloud Monitoring agent
    
    ```
        # Add the package repository and update the package list
        curl -sSO https://dl.google.com/cloudagents/add-monitoring-agent-repo.sh
        sudo bash add-monitoring-agent-repo.sh
        sudo apt-get update
        
        # Install the agent
        sudo apt-get install stackdriver-agent
        # 
        sudo service stackdriver-agent start
    ```
 * Configure credentials to push metrics to Stackdriver Monitoring
    * Provide the role `roles/monitoring.metricWriter` to the service account of Compute Engine VM
   
### Build the jar and push to GCP Compute Engine VM
* Build 
    * `mvn clean package`
* Push to Cloud storage bucket
    * `gsutil cp "target\demo-0.0.1-SNAPSHOT.jar" gs://<BUCKET_NAME>/demo.jar`
* Start the application.
    * `` 