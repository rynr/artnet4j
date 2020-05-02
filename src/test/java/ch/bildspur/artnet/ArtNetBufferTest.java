package ch.bildspur.artnet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ArtNetBufferTest {

  @Test
  public void verifyDefaultChannelSizeIs512() {
    final ArtNetBuffer artNetBuffer = new ArtNetBuffer();
    assertThat(artNetBuffer.getDmxData((short) 0, (short) 0).length, equalTo(512));
  }

  @Test
  public void verifyAUninitializedSubnetUniverseReturnsNulledResult() {
    final ArtNetBuffer artNetBuffer = new ArtNetBuffer(4);
    assertThat(artNetBuffer.getDmxData((short) 0, (short) 0), equalTo(new byte[] {0, 0, 0, 0}));
  }

  @Test
  public void verifyStoredDataCanBeRetrieved() {
    final ArtNetBuffer artNetBuffer = new ArtNetBuffer(4);
    final byte[] storedData = new byte[] {1, 2, 3, 4};

    artNetBuffer.setDmxData((short) 1, (short) 2, storedData);
    final byte[] retrievedData = artNetBuffer.getDmxData((short) 1, (short) 2);

    assertThat(retrievedData, equalTo(storedData));
  }

  @Test
  public void verifyAccessingNegativeSubnetsOrUniversesFail() {
    assertThrows(AssertionError.class, () -> new ArtNetBuffer().getDmxData((short) -1, (short) 0));
    assertThrows(AssertionError.class, () -> new ArtNetBuffer().getDmxData((short) 0, (short) -1));
    assertThrows(AssertionError.class,
        () -> new ArtNetBuffer(1).setDmxData((short) -1, (short) 0, new byte[] {0}));
    assertThrows(AssertionError.class,
        () -> new ArtNetBuffer(1).setDmxData((short) 0, (short) -1, new byte[] {0}));
  }

  @Test
  public void verifyStoringWrongSizeOfBufferFails() {
    assertThrows(IllegalArgumentException.class,
        () -> new ArtNetBuffer(2).setDmxData((short) 0, (short) -1, new byte[] {0}));
    assertThrows(IllegalArgumentException.class,
        () -> new ArtNetBuffer(1).setDmxData((short) 0, (short) -1, new byte[] {0, 0}));
  }
}
