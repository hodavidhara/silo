package com.hodavidhara.base;

import com.hodavidhara.util.TestUtil;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@SuppressWarnings("UnusedDeclaration")
public class DataNodeBootstrapper implements ApplicationListener<ContextRefreshedEvent>, Ordered {

    @Value("${test.es.create.node:true}")
    private boolean createNode;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (createNode) {
            // Start up a data node
            NodeBuilder.nodeBuilder().clusterName(TestUtil.getProperty("es.cluster.name")).node();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
