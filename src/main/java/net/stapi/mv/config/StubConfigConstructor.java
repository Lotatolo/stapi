package net.stapi.mv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

public class StubConfigConstructor extends Constructor {
    private static final Logger logger = LoggerFactory.getLogger(StubConfig.class);

    public StubConfigConstructor() {
        this.yamlConstructors.put(new Tag("!intarr"), new ConstructParamsDelayConfig());
    }

    private class ConstructParamsDelayConfig extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            return constructSequence((SequenceNode) node).stream().mapToInt(x -> Integer.parseInt(x.toString())).toArray();
        }
    }
}
