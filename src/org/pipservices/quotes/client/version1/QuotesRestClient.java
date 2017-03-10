package org.pipservices.quotes.client.version1;

import javax.ws.rs.core.*;

import org.pipservices.runtime.*;
import org.pipservices.runtime.clients.*;
import org.pipservices.runtime.config.*;
import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;

import com.sun.jersey.api.client.*;

public class QuotesRestClient extends RestClient implements IQuotesClient {
	/**
	 * Unique descriptor for the QuotesRestClient component
	 */
	public final static ComponentDescriptor Descriptor = new ComponentDescriptor(
		Category.Clients, "pip-services-quotes", "rest", "1.0"
	);

	public QuotesRestClient() {
		super(Descriptor, "quotes");
	}
			
	@Override
    public DataPage<Quote> getQuotes(String correlationId, FilterParams filter, PagingParams paging) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.get_quotes");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);
        	this.addFilterParams(params, filter);
        	this.addPagingParams(params, paging);

        	return _resource
    		.queryParams(params)
           	.type(MediaType.APPLICATION_JSON)
		    .get(new GenericType<DataPage<Quote>>() {});
        } finally {
        	timing.endTiming();
        }
    }
    
	@Override
    public Quote getRandomQuote(String correlationId, FilterParams filter) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.get_random_quote");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);
        	this.addFilterParams(params, filter);
        	
            return _resource.path("random")
        		.queryParams(params)
               	.type(MediaType.APPLICATION_JSON)
    		    .get(Quote.class);
        } catch (UniformInterfaceException ex) {
        	// Override 404 codes
        	if (ex.getResponse().getStatus() == 404)
        		return null;
        	throw ex;
        } finally {
        	timing.endTiming();
        }
    }

	@Override
    public Quote getQuoteById(String correlationId, String quoteId) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.get_quote_by_id");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);

        	return _resource.path(quoteId)
        	   .queryParams(params)
         	   .type(MediaType.APPLICATION_JSON)
    		   .get(Quote.class);
        } catch (UniformInterfaceException ex) {
        	// Override 404 codes
        	if (ex.getResponse().getStatus() == 404)
        		return null;
        	throw ex;
        } finally {
        	timing.endTiming();
        }
    }
    
	@Override
    public Quote createQuote(String correlationId, Quote quote) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.create_quote");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);

        	return _resource        	
        	   .queryParams(params)
        	   .type(MediaType.APPLICATION_JSON)
    		   .post(Quote.class, quote);
        } finally {
        	timing.endTiming();
        }
	}
	
	@Override
    public Quote updateQuote(String correlationId, String quoteId, Object quote) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.update_quote");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);

        	return _resource.path(quoteId)
        		.queryParams(params)
        	   .type(MediaType.APPLICATION_JSON)
    		   .put(Quote.class, quote);
        } catch (UniformInterfaceException ex) {
        	// Override 404 codes
        	if (ex.getResponse().getStatus() == 404)
        		return null;
        	throw ex;
        } finally {
        	timing.endTiming();
        }
    }

	@Override
    public void deleteQuote(String correlationId, String quoteId) throws MicroserviceError {
        checkCurrentState(State.Ready);

        ITiming timing = instrument(correlationId, "quotes.delete_quote");
        try {
        	MultivaluedMap<String, String> params = this.createQueryParams();
        	this.addCorrelationId(params, correlationId);

        	_resource.path(quoteId)
	    		.queryParams(params)
	    	   .type(MediaType.APPLICATION_JSON)
    		   .delete();
        } finally {
        	timing.endTiming();
        }
   }
	
}
