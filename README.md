# Quotes Microservice Client SDK for Java

This is a Java client SDK for [pip-services-quotes](https://github.com/pip-services/pip-services-quotes) microservice.
It provides an easy to use abstraction over communication protocols:

* HTTP/REST client

<a name="links"></a> Quick Links:

* [Development Guide](doc/Development.md)
* [API Version 1](doc/JavaClientApiV1.md)

## Install

Add pip-clients-quotes-1.0.0.jar client SDK into your project references

If you are using maven use the following dependency configuration

```xml
<dependency>
    <groupId>org.pipservices</groupId>
    <artifactId>pip-clients-quotes</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Use

Add **org.pipservices.runtime.?** and **org.pipservices.quotes.client.version1** imports
```java
import org.pipservices.runtime.*;
import org.pipservices.quotes.client.version1.*;
```

Define client configuration parameters that match configuration of the microservice external API
```java
// Client configuration
DynamicMap config = new DynamicMap(
    "transport.type", "http",
    "transport.host", "localhost",
    "transport.port", 8002
);
```

Instantiate the client and open connection to the microservice
```java
// Create the client instance
QuotesRestClient client = new QuotesRestClient(config);

// Connect to the microservice
client.open();
    
// Work with the microservice
...
```

Now the client is ready to perform operations
```java
// Create a new quote
Quote quote = new Quote();
quote.setText(new MultiString());
quote.getText().setEn("Get in hurry slowly");
quote.setAuthor(new MultiString());
quote.getAuthor().setEn("Russian proverb");
quote.setTags(new String[] { "time management" });
quote.setStatus("completed");

quote = client.createQuote(quote);
```

```cs
// Get the list of quotes on 'time management' topic
DataPage<Quote> quotePage = client.getQuotes(
    new FilterParams(
        "tags", "time management",
        "status", "completed"
    ),
    new PagingParams(0, 10);
);
```    

## Acknowledgements

This client SDK was created and currently maintained by *Sergey Seroukhov*.

