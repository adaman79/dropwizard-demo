# Dockerfile to create a machine with
# - basic linux functions (curl,wget,python, etc)
# - Lastest JDK installed
FROM ubuntu:latest

# Update the APT cache
RUN sed -i.bak 's/main$/main universe/' /etc/apt/sources.list
RUN apt-get update
RUN apt-get upgrade -y --force-yes

# Install and setup project dependencies
RUN apt-get install -y curl wget
RUN locale-gen en_US en_US.UTF-8

#prepare for Java download
RUN apt-get install -y python-software-properties
RUN apt-get install -y software-properties-common

#grab oracle java (auto accept licence)
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java7-installer

ADD target/dropwizard-kundenverwaltung-demo-1.0-SNAPSHOT.jar /
#ADD target/${project.artifactId}-${project.version}.jar
ADD target/classes/hello-world.yml /

RUN ["java", "-jar", "dropwizard-kundenverwaltung-demo-1.0-SNAPSHOT.jar", "db", "migrate", "hello-world.yml"]
#CMD ["java", "-jar", ${project.artifactId}-${project.version}.jar, "db migrate", "hello-world.yml"]
CMD ["java", "-jar", "dropwizard-kundenverwaltung-demo-1.0-SNAPSHOT.jar", "server", "hello-world.yml"]
#CMD ["java", "-jar", ${project.artifactId}-${project.version}.jar, "server", "hello-world.yml"]

EXPOSE 8080:8081
