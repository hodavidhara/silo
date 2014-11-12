package com.hodavidhara.silo.elasticsearch.admin;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ElasticSearchClient {

    public static final String MAIN_INDEX = "main";

    @Value("${es.cluster.name:elasticsearch}")
    private String clusterName;

    private Client client;

    public Client getClient() {
        if (client == null) {
            Node node = NodeBuilder.nodeBuilder().clusterName(clusterName).client(true).node();
            client = node.client();
        }
        return client;
    }
}
