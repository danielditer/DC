echo 'Stopping Tomcat service...'		
sudo systemctl stop tomcat8
sudo rm -f /var/lib/tomcat8/webapps/webapp.war
sudo rm -rf /var/lib/tomcat8/webapps/webapp
echo 'Sending war file...'
sudo cp -f /tmp/webapp.war /var/lib/tomcat8/webapps/webapp.war
sudo rm /tmp/webapp.war
echo 'Starting Tomcat service...'
sudo systemctl start tomcat8
