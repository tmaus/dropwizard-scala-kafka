# Scala / Kafka example for Dropwizard

This is a sample project using Dropwizard together with Scala and Kafka. 
I worked on it as part of an evaluation process for a new microservice framework we will be using moving 
forward. 
More details can be found [Dropwizard-Scala-Kafka] (http://maus.sg/index.php/2016/08/25/dropwizard-scala-kafka/)

You would use it to get a basic understanding on how DW can be used in conjunction with Kafka. 
I have not spent much time on it so I would refrain from using the Kafka part in any production environment. 


## Functionality
The endpoint (MyResources) '/api/publish/{msg}' publishes the message to Kafka. 
The 'KafkaTopicExampleConumer' consumes the message from Kafka and logs it out. 
   
 
## Sources

The 'config.yml' covers kafka related configuration settings. 

The 'MyApplication.scala' file contains the base class to configure and run the application. 

The 'Kafka.scala' file contains the manager extension and the consumer. 

The 'KafkaConfiguration' file contains Kafka related configuration informat .. I bet you knew that already ;)


 
## Prerequisites

http://www.dropwizard.io/1.0.0/docs/
 
https://github.com/datasift/dropwizard-scala (1.0.0-1)
 
http://kafka.apache.org/





