package ch.bildspur.artnet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.Test;

public class NodeStyleTest {

    @Test
    public void verifyStyleIdIsSameAsOrdinal() {
        for (NodeStyle nodeStyle : NodeStyle.values()) {
            assertThat(nodeStyle.getStyleID(), equalTo(nodeStyle.ordinal()));
        }
    }

    @Test
    public void verifyCanCreateArtnetNodeForAllNodeStyles() {
        for (NodeStyle nodeStyle : NodeStyle.values()) {
            ArtNetNode createdNode = nodeStyle.createNode();
            assertThat(createdNode.getClass(), instanceOf(nodeStyle.getNodeClass().getClass()));
        }
    }
}
