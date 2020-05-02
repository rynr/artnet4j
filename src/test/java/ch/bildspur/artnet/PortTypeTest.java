package ch.bildspur.artnet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PortTypeTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void expectPortTypeRetrievedCorrectly() throws ArtNetException {
        for (int ioModifier : List.of(0, 0x40, 0x80, 0x40 & 0x80)) {
            assertThat("Expect id of " + (0 | ioModifier) + " to be of Type DMX512", PortType.fromId(0 | ioModifier),
                    equalTo(PortType.DMX512));
            assertThat("Expect id of " + (1 | ioModifier) + " to be of Type MIDI", PortType.fromId(1 | ioModifier),
                    equalTo(PortType.MIDI));
            assertThat("Expect id of " + (2 | ioModifier) + " to be of Type AVAB", PortType.fromId(2 | ioModifier),
                    equalTo(PortType.AVAB));
            assertThat("Expect id of " + (3 | ioModifier) + " to be of Type COLORTRAN", PortType.fromId(3 | ioModifier),
                    equalTo(PortType.COLORTRAN));
            assertThat("Expect id of " + (4 | ioModifier) + " to be of Type ADB62_5", PortType.fromId(4 | ioModifier),
                    equalTo(PortType.ADB62_5));
            assertThat("Expect id of " + (5 | ioModifier) + " to be of Type ARTNET", PortType.fromId(5 | ioModifier),
                    equalTo(PortType.ARTNET));
        }
    }

    @Test
    public void expectIdToEqualOrdinal() {
        for (PortType portType : PortType.values()) {
            assertThat(portType.getPortID(), equalTo(portType.ordinal()));
        }
    }
    
    @Test
    public void verify() throws ArtNetException {
        thrown.expect(ArtNetException.class);
        thrown.expectMessage("Invalid PortType ID: 8");
        
        PortType.fromId(8);
    }
}
