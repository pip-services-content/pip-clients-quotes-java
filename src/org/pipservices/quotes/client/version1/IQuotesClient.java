package org.pipservices.quotes.client.version1;

import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;

/**
 * Interface for Quotes microservice clients version 1
 * 
 * @author Sergey Seroukhov
 * @version 1.0
 * @since 2016-06-21
 */
public interface IQuotesClient {
    DataPage<Quote> getQuotes(String correlationId, FilterParams filter, PagingParams paging) throws MicroserviceError;
    Quote getRandomQuote(String correlationId, FilterParams filter) throws MicroserviceError;
    Quote getQuoteById(String correlationId, String quoteId) throws MicroserviceError;
    Quote createQuote(String correlationId, Quote quote) throws MicroserviceError;
    Quote updateQuote(String correlationId, String quoteId, Object quote) throws MicroserviceError;
    void deleteQuote(String correlationId, String quoteId) throws MicroserviceError;
}