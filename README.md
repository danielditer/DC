# DC

## JAVA APPS DEPLOYMENT

* **Using Maven** 

  To deploy your app with Maven, run the following command from your project's top level directory, where the pom.xml file is located.
  
  If you are using the App Engine SDK-based Maven plugin, use the command:
  
  `mvn appengine:update`
  
  If you are using the Cloud SDK-based Maven plugin, use the command:
  
  `mvn appengine:deploy`

  * **Deploying Java Applications to Heroku from IntelliJ IDEA**
    
    To deploy, select Run -> Edit Configurations from the IntelliJ IDEA menu. 
    
    Then select the + button to create a new Maven configuration.
    Then name the configuration “Heroku Deploy”, and enter heroku:deploy-war in the “Command Line” field. 
    
    If you were deploying a standalone Java application, you could use the `heroku:deploy` target to deploy without a WAR file.
    
    Click “OK”. Now a “Heroku Deploy” task will be shown in the Run Configurations toolbar. Press the green arrow button next to it.
    
    Open your application by running `$ heroku open -a <app-name>` from the command-line, or by browsing to `http://<app-name>.herokuapp.com` (replace <`app-name` with the name of your app).
    
 * **Deploying to Tomcat using Ant**
   
   The project contains the build.xml and build.properties at the root level of the project.
   
   The build.xml file contains 4 taskdef entries, named start, stop, deploy, and undeploy. Each of these tasks represents an action that can be carried out on an application on the Tomcat server. Each calls on a JavaSW class to perform a particular action, and various values are passed to the class via attribute values in the build.xml file.
   
   The 'deploy' target first outputs a message to the console using the 'echo' task. Following this, it executes the 'deploy' task, which passes the 'url', 'username', 'password', 'path', and 'war' values to the DeployTask Java class referenced in the taskdef entry in build.xml.
   
   The 'url' attribute is the URL to the TomcatSW Manager application. The 'username' and 'password' are the Tomcat username and password that you'd like to use to connect to the Tomcat Manager application. Note that you should have 'manager' access in Tomcat, which can be checked in your Tomcat server's tomcat-users.xml file.
   
   The 'deploy' task's 'path' attribute represents the context (ie, path) that Tomcat should use to allow users to access the application. The 'deploy' task's 'war' attribute represents the path to our copy of the warW file of our project that AntSW builds.
   
   If I double-click the 'deploy' task for tomcat-demo in Eclipse's Ant view, the war file built by the script will be deployed to the Tomcat server.
  
 * **How to deploy Java apps with Docker**
 
   **Step 1: install Docker**
   
   First we install some kernel extensions needed for it to run:
   
   `sudo apt-get install linux-image-extra-$(uname -r)`
   
   Then we install software-properties-common which provides us with add-apt-repository:
   
   `sudo apt-get install software-properties-common`
   
   Add the dotcloud Personal Package Archive (PPA):
   
   `sudo add-apt-repository ppa:dotcloud/lxc-docker`
   
   `sudo apt-get update`
   
   And finally install Docker with:
   
   `sudo apt-get install lxc-docker`
   
   Now we are ready to pull a base image which will be the foundation of all our work:
   
   `docker pull base`
   
   You can pull any public image published on the Docker index or publish your own. And you can inspect all the images that Docker has at its disposal with:
   
   `docker images`
   
   **Step 2: install Java inside Docker**
   
   Now we’re on our way to creating an image and customizing it to our needs. The first requirement is to install Java. Let’s spin up a shell in the base container:
   
   `docker run -i -t base /bin/bash`
   
   This starts a new container, gives it a unique id, assigns it an IP address and setups networking to it. We’re greeted with a root shell:
   
   `root@298af82e71ef:/#`
   
   Now in this container – which is a lightweight and sealed vm-like thing separate from all the rest – we can install our dependencies:
   
   `apt-get install software-properties-common`
   
   Add the PPA that will allow us to install Java:
   
   `add-apt-repository ppa:webupd8team/java`
   
   `apt-get update`
   
   Then finally, install Java:
   
   `apt-get install git curl oracle-java7-installer`
   
   Ok. Now we can create a commit to save the state of this container in an image.
   
   From the list of recent containers we take the most recent, where we installed all the dependencies. Now we can commit a new snapshot/image with docker commit:
   
   `docker commit 8e07a84ea97a durdn/java7`
   
   **Step 3: install the app in your Docker container**
   
   Now we can install our Java application – in this case Bitbucket Server – in our newly created durdn/java7 image.
   
   Spin up a shell in the newly created durdn/java7 image opening a mirror port 7990 from the container to the host and with a persistent home where the data will be stored:
   
   `docker run -i -t -p :7990 -v /opt/stash-home durdn/java7 /bin/bash
   root@298af82e71ef:/#`
   
   Download Bitbucket Server. (Note that when this post was originally published, Bitbucket Server was called “Stash”. You’ll see that here in some of the file and directory names, as well as the screenshot below
   
   `root@298af82e71ef:/# curl -Lks https://www.atlassian.com/software/stash/downloads/binary/atlassian-bitbucket-4.13.0.tar.gz -o /root/stash.tar.gz`
  
   Unpack stash, create and export STASH_HOME folder:
   
   `root@298af82e71ef:/# mkdir /opt/stash`
   
   `root@298af82e71ef:/# tar zxf /root/stash.tar.gz --strip=1 -C /opt/stash`
   
   `root@298af82e71ef:/# mkdir /opt/stash-home`
   
   `root@298af82e71ef:/# export STASH_HOME=/opt/stash-home`
   
   To avoid startup errors we must add the unique id of this host 298af82e71ef to the /etc/hosts directory.
   
   Now we can start the app:
   
   `root@298af82e71ef:/# /opt/stash/bin/start-stash.sh -fg`
   
   Check that Stash Bitbucket Server is running in the container by accessing http://localhost:7990/stash. It works:
   
   `stash-login`
   
   We can now exit this container and commit it so that we can reuse the work we did:
   
   `docker commit aec2feb8cdea durdn/stash`
   
   `effd5d47b34f`
