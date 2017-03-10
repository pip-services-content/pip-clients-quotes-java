# Development and Testing Guide <br/> Quotes Microservice Client SDK for Java

This document provides high-level instructions on how to build and test the client SDK.

* [Environment Setup](#setup)
* [Installing](#install)
* [Building](#build)
* [Testing](#test)
* [Contributing](#contrib) 

## <a name="setup"></a> Environment Setup

Pip.Services runtime uses Java 8 features like streams and time api and requires JDK version 1.8.0 or higher.

The project uses Maven-based build infrastructure.
You can download it from apache foundation website at: https://maven.apache.org/download.cgi 

To work with GitHub code repository you need to install Git from: https://git-scm.com/downloads

## <a name="install"></a> Installing

After your environment is ready you can check out microservice source code from the Github repository:
```bash
git clone git@github.com:pip-services/pip-clients-quotes-java.git
```

## <a name="build"></a> Building

Maven makes it very easy to download all required dependencies, compile and test the code.

To create deployable package use the following command:
```bash
mvn package
```

To install the client sdk into your local repository to make it available to application projects:
```bash
mvn install
```

## <a name="test"></a> Testing

Unit testing in the Java client SDK doesn't run microservice and relies on external Node.js microservice instance.
So, follow instructions to install and run the microservice at https://github.com/pip-services/pip-services-quotes

Make sure you enable REST API endpoint in the microservice. The default microservice HTTP REST port is 8002.
Then check rest configuration in unit tests to match the microservice port. 

When Node.js microservices is up and running you can execute the following command:
```bash
mvn test
```

## <a name="contrib"></a> Contributing

Developers interested in contributing should read the following instructions:

- [How to Contribute](http://www.pipservices.org/contribute/)
- [Guidelines](http://www.pipservices.org/contribute/guidelines)
- [Styleguide](http://www.pipservices.org/contribute/styleguide)
- [ChangeLog](CHANGELOG.md)

> Please do **not** ask general questions in an issue. Issues are only to report bugs, request
  enhancements, or request new features. For general questions and discussions, use the
  [Contributors Forum](http://www.pipservices.org/forums/forum/contributors/).

It is important to note that for each release, the [ChangeLog](CHANGELOG.md) is a resource that will
itemize all:

- Bug Fixes
- New Features
- Breaking Changes