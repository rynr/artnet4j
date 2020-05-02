package ch.bildspur.artnet;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class NodeStyleTest {

    @Test
    public void verifyStyleIdIsSameAsOrdinal() {
        for (NodeStyle nodeStyle : NodeStyle.values()) {
            assertThat(nodeStyle.getStyleID(), CoreMatchers.equalTo(nodeStyle.ordinal()));
        }
    }

    @Test
    public void verifyCanCreateArtnetNodeForAllNodeStyles() {
        for (NodeStyle nodeStyle : NodeStyle.values()) {
            ArtNetNode createdNode = nodeStyle.createNode();
            assertThat(createdNode.getClass(), CoreMatchers.instanceOf(nodeStyle.getNodeClass()));
        }
    }
}
