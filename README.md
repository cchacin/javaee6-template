[![Build Status](https://travis-ci.org/cchacin/javaee6-template.svg?branch=master)](https://travis-ci.org/cchacin/javaee6-template)
[![Coverage Status](https://coveralls.io/repos/cchacin/javaee6-template/badge.png?branch=master)](https://coveralls.io/r/cchacin/javaee6-template?branch=master)

# Java EE 6 Template Application
This a template for Java EE 6 using [Apache TomEE Plus 1.6.0.2](http://tomee.apache.org/) as the default server.

* *Author* : [Carlos Chacin](http://www.twitter.com/carloschacin)
* *Summary* : An application using Java EE 6 with MySQL as database, Redis as key/value cache

[Download the code from GitHub](https://github.com/cchacin/javaee6-template)

## Reporting
### [Javadoc](http://cchacin.github.io/javaee6-template/apidocs/)
### [Code Coverage](http://cchacin.github.io/javaee6-template/cobertura/)

# [TomEE](http://tomee.apache.org/)
Apache TomEE, pronounced "Tommy",is an all-Apache Java EE 6 Web Profile certified stack where Apache Tomcat is top dog. 
Apache TomEE is assembled from a vanilla Apache Tomcat zip file. We start with Apache Tomcat, add our jars and zip up the rest.
The result is Tomcat with added EE features - TomEE.

# Technologies:

## [OpenJPA](http://openjpa.apache.org/)
Apache OpenJPA is a Java persistence project at The Apache Software Foundation that can be used as a stand-alone POJO persistence layer or integrated into any Java EE compliant container and many other lightweight frameworks, such as Tomcat and Spring.

## [CXF](http://cxf.apache.org/)
Apache CXF is an open source services framework. CXF helps you build and develop services using frontend programming APIs, like JAX-WS and JAX-RS. These services can speak a variety of protocols such as SOAP, XML/HTTP, RESTful HTTP, or CORBA and work over a variety of transports such as HTTP, JMS or JBI.

## [OpenWebBeans](http://openwebbeans.apache.org/)
Apache OpenWebBeans delivers an implementation of the Contexts and Dependency injection for Java EE (CDI) 1.0 Specification (JSR299).
OpenWebBeans is TCK compliant and works on Java SE 5 or later.

## [BVal](http://bval.apache.org/)
Apache BVal delivers an implementation of the Java Bean Validation (JSR303) Specification which is TCK compliant, works on Java SE 5 or later, and uses the Apache Software License v2.0.

# External Libraries

## [Redisson](https://github.com/mrniko/redisson)
Redisson - distributed and scalable Java data structures (Set, SortedSet, Map, ConcurrentMap, List, Queue, Deque, Lock, AtomicLong, CountDownLatch, Publish / Subscribe, HyperLogLog) on top of Redis server. Advanced redis java client

## [DeltaSpike](http://deltaspike.apache.org/index.html)
DeltaSpike consists of a number of portable CDI extensions that provide useful features for Java application developers.

## [Jackson](https://github.com/FasterXML/jackson)
Jackson is a suite of data-processing tools for Java (and JVM platform), including the flagship JSON parsing and generation library, as well as additional modules to process data encoded in Avro, CBOR, CSV, Smile, XML or YAML (and list of supported format is still growing!)
