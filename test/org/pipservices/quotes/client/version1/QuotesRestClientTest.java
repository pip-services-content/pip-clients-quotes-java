package org.pipservices.quotes.client.version1;

import org.junit.*;

import org.pipservices.runtime.*;
import org.pipservices.runtime.config.*;
import org.pipservices.runtime.errors.*;
import org.pipservices.runtime.run.*;

public class QuotesRestClientTest {
	private static final ComponentConfig RestOptions = ComponentConfig.fromTuples(
        "descriptor.type", "rest",
        "endpoint.protocol", "http",
        "endpoint.host", "localhost",
        "endpoint.port", 8002
	);

    private QuotesRestClient client;
    private ComponentSet components;
    private QuotesClientFixture fixture;

    public QuotesRestClientTest() throws MicroserviceError {
        client = new QuotesRestClient();
        client.configure(RestOptions);
        
        components = ComponentSet.fromComponents(client);
        fixture = new QuotesClientFixture(client);
    }
    
	@Before
	public void setUp() throws Exception {
		LifeCycleManager.linkAndOpen(components);
	}

	@After
	public void tearDown() throws Exception {
		LifeCycleManager.close(components);
	}

	@Test
	public void testCrudOperations() throws MicroserviceError {
		fixture.testCrudOperations();
	}

}
