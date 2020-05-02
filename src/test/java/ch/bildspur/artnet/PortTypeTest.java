package ch.bildspur.artnet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PortTypeTest {

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
    public void verifyInvalidIdsCauseException() throws ArtNetException {
        ArtNetRuntimeException exception = assertThrows(ArtNetRuntimeException.class, () -> {
            PortType.fromId(8);
        });
        assertThat(exception.getMessage(), equalTo("Invalid PortType ID: 8"));
    }
}
