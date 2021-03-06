// code by jph
package ch.ethz.idsc.retina.lcm.davis;

import java.nio.ByteBuffer;

import ch.ethz.idsc.retina.dev.davis.DavisApsType;
import ch.ethz.idsc.retina.dev.davis.data.DavisApsBlockListener;
import idsc.BinaryBlob;
import lcm.lcm.LCM;

public class DavisApsBlockPublisher implements DavisApsBlockListener {
  /** @param cameraId
   * @param davisApsType
   * @return channel name for given id */
  public static String channel(String cameraId, DavisApsType davisApsType) {
    return DavisLcmStatics.CHANNEL_PREFIX + "." + cameraId + "." + davisApsType.name().toLowerCase();
  }

  // ---
  private final LCM lcm = LCM.getSingleton();
  private final String channel;

  public DavisApsBlockPublisher(String cameraId, DavisApsType davisApsType) {
    channel = channel(cameraId, davisApsType);
  }

  @Override
  public void apsBlock(int length, ByteBuffer byteBuffer) {
    BinaryBlob binaryBlob = new BinaryBlob();
    binaryBlob.data_length = length;
    binaryBlob.data = new byte[length]; // TODO try assigning byte buf array
    byteBuffer.get(binaryBlob.data);
    lcm.publish(channel, binaryBlob);
  }
}
