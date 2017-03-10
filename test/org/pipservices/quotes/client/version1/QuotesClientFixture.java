package org.pipservices.quotes.client.version1;

import static org.junit.Assert.*;

import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;

public class QuotesClientFixture {
    private final Quote QUOTE1 = createQuote("Text 1", "Author 1", null);
    private final Quote QUOTE2 = createQuote("Text 2", "Author 2", new String[] { "TAG 1" });

    private IQuotesClient _client;

    public QuotesClientFixture(IQuotesClient client) {
        assertNotNull(client);
        _client = client;
    }

    private static Quote createQuote(String text, String author, String[] tags) {
    	Quote quote = new Quote();
    	
    	if (text != null) {
	    	quote.setText(new MultiString());
	    	quote.getText().setEn(text);
    	}

    	if (author != null) {
	    	quote.setAuthor(new MultiString());
	    	quote.getAuthor().setEn(author);
    	}

    	if (tags != null)
    		quote.setTags(tags);
    	
    	quote.setStatus("new");
    	
    	return quote;
    }
    
    public void testCrudOperations() throws MicroserviceError {
        // Create one quote
        Quote quote1 = _client.createQuote(null, QUOTE1);

        assertNotNull(quote1);
        assertNotNull(quote1.getId());
        assertEquals(QUOTE1.getText().getEn(), quote1.getText().getEn());
        assertEquals(QUOTE1.getAuthor().getEn(), quote1.getAuthor().getEn());

        // Create another quote
        Quote quote2 = _client.createQuote(null, QUOTE2);

        assertNotNull(quote2);
        assertNotNull(quote2.getId());
        assertEquals(QUOTE2.getText().getEn(), quote2.getText().getEn());
        assertEquals(QUOTE2.getAuthor().getEn(), quote2.getAuthor().getEn());

        // Get all quotes
        DataPage<Quote> quotes = _client.getQuotes(null, null, null);
        assertNotNull(quotes);
        assertNotNull(quotes.getData());
        assertTrue(quotes.getData().size() >= 2);

        // Get random quote
        Quote randomQuote = _client.getRandomQuote(null, null);
        assertNotNull(randomQuote);

        // Update the quote
        quote1.getText().setEn("Updated Content 1");
        Quote quote = _client.updateQuote(
    		null,
            quote1.getId(),
            quote1
        );

        assertNotNull(quote);
        assertEquals(quote1.getId(), quote.getId());
        assertEquals(quote1.getAuthor().getEn(), quote.getAuthor().getEn());
        assertEquals("Updated Content 1", quote.getText().getEn());

        // Delete the quote #1
        _client.deleteQuote(null, quote1.getId());

        // Delete the quote #2
        _client.deleteQuote(null, quote2.getId());

        // Try to get deleted quote
        quote = _client.getQuoteById(null, quote1.getId());
        assertNull(quote);
    }
}