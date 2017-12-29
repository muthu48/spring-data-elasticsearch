package com.jpw.elastic.config;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.elasticsearch.common.settings.ImmutableSettings; 

@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.jpw.elastic.repository")
public class ElasticConfiguration {

	@Resource
    private Environment environment;

	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;
	    
/*    @Bean
    public NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }
*/
/*    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws IOException {
        File tmpDir = File.createTempFile("elastic", Long.toString(System.nanoTime()));
        File elasticDBLocation = new File("C:/Users/OPSKY/Documents/Project/Software/elasticsearch-6.1.0/data");
        System.out.println("Temp directory: " + tmpDir.getAbsolutePath());
        Settings.Builder elasticsearchSettings =
                Settings.settingsBuilder()
                        .put("http.enabled", "false") // 1
                        .put("index.number_of_shards", "1")
                        .put("path.data", elasticDBLocation.getAbsolutePath()) // 2
                        .put("path.logs", new File(tmpDir, "logs").getAbsolutePath()) // 2
                        .put("path.work", new File(tmpDir, "work").getAbsolutePath()) // 2
                        .put("path.home", new File("C:/Users/OPSKY/Documents/Project/Software/elasticsearch-6.1.0").getAbsolutePath()); // 3



        return new ElasticsearchTemplate(nodeBuilder()
                .local(true)
                .settings(elasticsearchSettings.build())
                .node()
                .client());
    }*/
    

    @Bean
    public Client client() {
        /*TransportClient client = null;
		client = TransportClient.builder().build()
				.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
		        .addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), 9300));
*/
		
		TransportClient client = TransportClient.builder().build()
		        .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(esHost, esPort)));

		
/*    	RestHighLevelClient client = new RestHighLevelClient(
    	        RestClient.builder(
    	                new HttpHost("localhost", 9300, "http")));
		*/
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
