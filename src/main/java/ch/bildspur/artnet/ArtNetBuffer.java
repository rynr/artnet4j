package ch.bildspur.artnet;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * Implementation of a storage for any number of channels withing subnets and universes.
 */
public class ArtNetBuffer {
  private static final int DEFAULT_CHANNEL_SIZE = 512;

  @Getter
  private final int channelSize;
  private final Map<Integer, byte[]> data;

  public ArtNetBuffer() {
    this(DEFAULT_CHANNEL_SIZE);
  }

  public ArtNetBuffer(final int channelSize) {
    this.channelSize = channelSize;
    data = new HashMap<>();
  }

  public byte[] getDmxData(final short subnet, final short universe) {
    final int key = hashKeyFromPair(subnet, universe);
    return data.containsKey(key) ? data.get(key) : new byte[channelSize];
  }

  public void setDmxData(final short subnet, final short universe, final byte[] dmxData) {
    if (dmxData.length != channelSize) {
      throw new IllegalArgumentException(MessageFormat.format(
          "Invalid channel size for buffer (expected {0}, got {1)", channelSize, dmxData.length));
    }
    data.put(hashKeyFromPair(subnet, universe), dmxData);
  }

  public void clear() {
    data.clear();
  }

  private static int hashKeyFromPair(final short a, final short b) {
    assert a >= 0;
    assert b >= 0;
    final int sum = a + b;
    return sum * (sum + 1) / 2 + a;
  }
}
