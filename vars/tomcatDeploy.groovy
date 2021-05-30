def call(credId, user, ip)
{
  sshagent([credId]) {
    // rename the war file
    sh "mv target/*.war target/myweb.war"
    // copy war file from jenkins to tomcat webapps folder
    sh "scp -o StrictHostKeyChecking=no target/myweb.war ${user}@${ip}:/opt/tomcat8/webapps"
    // stop and start tomcat
    sh "ssh ec2-user@${ip} /opt/tomcat8/bin/shutdown.sh"
    sh "ssh ec2-user@${ip} /opt/tomcat8/bin/startup.sh"
  }
}
