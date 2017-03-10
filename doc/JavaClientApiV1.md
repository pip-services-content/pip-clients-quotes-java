# Client API (version 1) <br/> Quotes Microservices Client SDK for Java

Java client API for Quotes microservice is a thin layer on the top of
communication protocols. It hides details related to specific protocol implementation
and provides high-level API to access the microservice for simple and productive development.

* [Installation](#install)
* [Getting started](#get_started)
* [MultiString class](#class1)
* [Quote class](#class2)
* [QuotePage class](#class3)
* [IQuoteClient interface](#interface)
    - [init()](#operation1)
    - [open()](#operation2)
    - [close()](#operation3)
    - [getQuotes()](#operation4)
    - [getRandomQuote()](#operation5)
    - [getQuoteById()](#operation6)
    - [createQuote()](#operation7)
    - [updateQuote()](#operation8)
    - [deleteQuote()](#operation9)
* [QuotesRestClient class](#client_rest)

## <a name="install"></a> Installation

To work with the client SDK add to your classpath pip-services-runtime-1.0.0.jar and pip-clients-quotes-1.0.0.jar

If you don't have them readily available then download and build them from sourcecode
- Pip.Services Java Runtime: https://github.com/pip-services/pip-services-runtime-java
- This Java Client SDK for Quotes microservices: https://github.com/pip-services/pip-clients-quotes-java 

Alternatively, you can use the following Maven dependencies:
```xml
<dependency>
    <groupId>org.pipservices</groupId>
    <artifactId>pip-services-runtime</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>org.pipservices</groupId>
    <artifactId>pip-clients-quotes</artifactId>
    <version>1.0.0</version>
</dependency>
```

## <a name="get_started"></a> Getting started

This is a simple example on how to work with the microservice using REST client:

```java
package sample;

import org.pipservices.runtime.*;
import org.pipservices.quotes.client.version1.*;

public class Program {
    public static void main(String[] args) {
        // Client configuration
		DynamicMap config = new DynamicMap(
			"transport.type", "http",
			"transport.host", "localhost",
			"transport.port", 8002
		);
  
        // Create the client instance
		QuotesRestClient client = new QuotesRestClient(config);
        
        try {
             // Open client connection to the microservice
             client.open();             
             System.out.println("Opened connection");

             // Create a new quote
            Quote quote = new Quote();
            quote.setText(new MultiString());
            quote.getText().setEn("Get in hurry slowly");
            quote.setAuthor(new MultiString());
            quote.getAuthor().setEn("Russian proverb");
            quote.setTags(new String[] { "time management" });
            quote.setStatus("completed");

            quote = client.createQuote(quote);
            System.out.println("Create quote is");
            System.out.println(quote);

            // Get the list of quotes on 'time management' topic
            DataPage<Quote> quotePage = client.getQuotes(
                new FilterParams(
                    "tags", "time management",
                    "status", "completed"
                ),
                new PagingParams(0, 10);
            );

            System.out.println("Quotes on time management are");
            System.out.println(quotesPage.getData());
            
            // Close connection
            client.close(); 
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
```

### <a name="class1"></a> MultiString class

String that contains versions in multiple languages

**Properties:**
- En: string - English version of the string
- Sp: string - Spanish version of the string
- De: string - German version of the string
- Fr: string - Franch version of the string
- Pt: string - Portuguese version of the string
- Ru: string - Russian version of the string
- .. - other languages can be added here

### <a name="class2"></a> Quote class

Represents an inspirational quote

**Properties:**
- Id: string - unique quote id
- Text: MultiString - quote text in different languages
- Author: MultiString - name of the quote author in different languages
- Status: string - editing status of the quote: 'new', 'writing', 'translating', 'completed' (default: 'new')
- Tags: string[] - (optional) search tags that represent topics associated with the quote
- AllTags: string[] - (read only) explicit and hash tags in normalized format for searching  

### <a name="class3"></a> QuotePage class

Represents a paged result with subset of requested quotes

**Properties:**
- Data: List&lt;Quote&gt; - array of retrieved Quote page
- Total: Integer - total number of objects in retrieved resultset

## <a name="interface"></a> IQuotesClient interface

IQuotesClient as a common interface across all client implementations. 

```cs
public interface IQuotesClient {
    public void init(References refs) throws MicroserviceError;
    public void open() throws MicroserviceError;
    public void close() throws MicroserviceError;
    public DataPage<Quote> getQuotes(FilterParams filter, PagingParams paging) throws MicroserviceError;
    public Quote getRandomQuote(FilterParams filter) throws MicroserviceError;
    public Quote getQuoteById(String quoteId) throws MicroserviceError;
    public Quote createQuote(Quote quote) throws MicroserviceError;
    public Quote updateQuote(String quoteId, Quote quote) throws MicroserviceError;
    public void deleteQuote(String quoteId) throws MicroserviceError;
}
```

### <a name="operation1"></a> init(refs)

Initializes client references. This method is optional. It is used to set references 
to logger or performance counters.

**Arguments:**
- refs: References - references to other components 
  - Log: ILog - reference to logger
  - Countes: ICounters - reference to performance counters

### <a name="operation2"></a> open()

Opens connection to the microservice

### <a name="operation3"></a> close()

Closes connection to the microservice

### <a name="operation4"></a> getQuotes(filter, paging)

Retrieves a collection of quotes according to specified criteria

**Arguments:** 
- filter: FilterParams - filter parameters
  - tags: string[] - (optional) list tags with topic names
  - status: string - (optional) quote editing status
  - author: string - (optional) author name in any language 
  - except_ids: string[] - (optional) quote ids to exclude 
- paging: PagingParams - paging parameters
  - skip: int - (optional) start of page (default: 0). Operation returns paged result
  - take: int - (optional) page length (max: 100). Operation returns paged result
  - paging: bool - (optional) true to enable paging and return total count

**Returns**
  - DataPage&lt;Quote&gt; - retrieved quotes in paged format

### <a name="operation5"></a> getRandomQuote(filter)

Retrieves a random quote from filtered resultset

**Arguments:** 
- filter: FilterParams - filter parameters
  - tags: string[] - (optional) list tags with topic names
  - status: string - (optional) quote editing status
  - author: string - (optional) author name in any language
  - except_ids: string[] - (optional) quote ids to exclude
  
**Returns** 
- Quote - random quote, null if object wasn't found 

### <a name="operation6"></a> getQuoteById(quoteId)

Retrieves a single quote specified by its unique id

**Arguments:** 
- quoteId: string - unique Quote id

**Returns**
- Quote - retrieved quote, null if object wasn't found 

### <a name="operation7"></a> createQuote(quote)

Creates a new quote

**Arguments:** 
- quote: Quote - Quote object to be created. If object id is not defined it is assigned automatically.

**Returns**
- Quote - created quote object

### <a name="operation8"></a> updateQuote(quoteId, quote)

Updates quote specified by its unique id

**Arguments:** 
- quoteId: string - unique quote id
- quote: Quote - quote object with new values. Partial updates are supported

**Returns**
- Quote - updated quote object 

### <a name="operation9"></a> deleteQuote(quoteId)

Deletes quote specified by its unique id

**Arguments:** 
- quoteId: string - unique quote id
 
## <a name="client_rest"></a> QuotesRestClient class

QuotesRestClient is a client that implements HTTP/REST protocol

```cs
public class QuotesRestClient extends RestClient implements IQuotesClient {
    public QuotesRestClient(DynamicMap config);
    public void init(References refs) throws MicroserviceError;
    public void open() throws MicroserviceError;
    public void close() throws MicroserviceError;
    public DataPage<Quote> getQuotes(FilterParams filter, PagingParams paging) throws MicroserviceError;
    public Quote getRandomQuote(FilterParams filter) throws MicroserviceError;
    public Quote getQuoteById(String quoteId) throws MicroserviceError;
    public Quote createQuote(Quote quote) throws MicroserviceError;
    public Quote updateQuote(String quoteId, Quote quote) throws MicroserviceError;
    public void deleteQuote(String quoteId) throws MicroserviceError;
}
```

**Constructor config properties:** 
- transport: Object - HTTP transport configuration options
  - type: String - HTTP protocol - 'http' or 'https' (default is 'http')
  - host: String - IP address/hostname binding (default is '0.0.0.0')
  - port: int - HTTP port number
