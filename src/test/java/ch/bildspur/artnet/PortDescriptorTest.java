package ch.bildspur.artnet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class PortDescriptorTest {

    @Test
    public void verifySomeIdAbilityMappings() {
        Map<Integer, List<Object>> testcases = Map.of(
                0, List.of(false, false, PortType.DMX512),
                65, List.of(true, false, PortType.MIDI),
                130, List.of(false, true, PortType.AVAB),
                195, List.of(true, true, PortType.COLORTRAN));

        for (Entry<Integer, List<Object>> testcase : testcases.entrySet()) {
            PortDescriptor portDescriptor = new PortDescriptor(testcase.getKey());

            assertThat(portDescriptor.canInput(), equalTo(testcase.getValue().get(0)));
            assertThat(portDescriptor.canOutput(), equalTo(testcase.getValue().get(1)));
            assertThat(portDescriptor.getType(), equalTo(testcase.getValue().get(2)));
            assertThat(portDescriptor.getData(), equalTo(testcase.getKey()));
        }
    }

    @Test
    public void verifyCloneHasInputAbilityAsRequested() {
        PortDescriptor noInputNoOutput = new PortDescriptor(0); // no input, no output
        PortDescriptor inputNoOutput = new PortDescriptor(64); // no input, no output
        PortDescriptor noInputOutput = new PortDescriptor(128); // no input, no output
        PortDescriptor inputOutput = new PortDescriptor(192); // no input, no output

        assertThat(noInputNoOutput.setCanInput(true), equalTo(new PortDescriptor(64)));
        assertThat(noInputNoOutput.setCanInput(false), equalTo(new PortDescriptor(0)));
        assertThat(inputNoOutput.setCanInput(true), equalTo(new PortDescriptor(64)));
        assertThat(inputNoOutput.setCanInput(false), equalTo(new PortDescriptor(0)));
        assertThat(noInputOutput.setCanInput(true), equalTo(new PortDescriptor(192)));
        assertThat(noInputOutput.setCanInput(false), equalTo(new PortDescriptor(128)));
        assertThat(inputOutput.setCanInput(true), equalTo(new PortDescriptor(192)));
        assertThat(inputOutput.setCanInput(false), equalTo(new PortDescriptor(128)));
    }

    @Test
    public void verifyCloneHasOutputAbilityAsRequested() {
        PortDescriptor noInputNoOutput = new PortDescriptor(0); // no input, no output
        PortDescriptor inputNoOutput = new PortDescriptor(64); // no input, no output
        PortDescriptor noInputOutput = new PortDescriptor(128); // no input, no output
        PortDescriptor inputOutput = new PortDescriptor(192); // no input, no output

        assertThat(noInputNoOutput.setCanOutput(true), equalTo(new PortDescriptor(128)));
        assertThat(noInputNoOutput.setCanOutput(false), equalTo(new PortDescriptor(0)));
        assertThat(inputNoOutput.setCanOutput(true), equalTo(new PortDescriptor(192)));
        assertThat(inputNoOutput.setCanOutput(false), equalTo(new PortDescriptor(64)));
        assertThat(noInputOutput.setCanOutput(true), equalTo(new PortDescriptor(128)));
        assertThat(noInputOutput.setCanOutput(false), equalTo(new PortDescriptor(0)));
        assertThat(inputOutput.setCanOutput(true), equalTo(new PortDescriptor(192)));
        assertThat(inputOutput.setCanOutput(false), equalTo(new PortDescriptor(64)));
    }

    @Test
    public void verifyCloneHasTypeAbilityAsRequested() {
        PortDescriptor noInputNoOutput = new PortDescriptor(0); // no input, no output
        PortDescriptor inputNoOutput = new PortDescriptor(64); // no input, no output
        PortDescriptor noInputOutput = new PortDescriptor(128); // no input, no output
        PortDescriptor inputOutput = new PortDescriptor(192); // no input, no output

        for (PortType portType : PortType.values()) {
            assertThat(noInputNoOutput.setType(portType).getType(), equalTo(portType));
            assertThat(inputNoOutput.setType(portType).getType(), equalTo(portType));
            assertThat(noInputOutput.setType(portType).getType(), equalTo(portType));
            assertThat(inputOutput.setType(portType).getType(), equalTo(portType));
        }
    }
}
