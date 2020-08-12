FROM centos

RUN yum install -y java

VOLUME /tmp

ADD /RestDocsTutorial-0.0.1-SNAPSHOT.jar restapi.jar

RUN sh -c 'touch /restapi.jar'

ENTRYPOINT ["java","-jar","/restapi.jar"]



