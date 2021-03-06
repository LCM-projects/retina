// code by jph
package ch.ethz.idsc.retina.dev.davis.data;

import java.nio.ByteBuffer;

import ch.ethz.idsc.retina.dev.davis.DavisApsEventListener;
import ch.ethz.idsc.retina.dev.davis.DavisStatics;
import ch.ethz.idsc.retina.dev.davis._240c.DavisApsEvent;

/** conceptual sequence
 * aps 1151355 ( 194, 177) 563
 * aps 1151355 ( 194, 178) 538
 * aps 1151355 ( 194, 179) 538
 * aps 1151435 ( 195, 0) 612
 * aps 1151435 ( 195, 1) 615
 * aps 1151435 ( 195, 2) 618 */
// TODO code is not sufficiently generic due to the magic const
public class DavisApsColumnCompiler implements DavisApsEventListener {
  static final int LAST_Y = 179;
  static final int LENGTH = 4 + 180;
  // ---
  final byte[] data;
  final ByteBuffer byteBuffer;
  final DavisApsColumnListener davisApsColumnListener;

  public DavisApsColumnCompiler(DavisApsColumnListener davisApsColumnListener) {
    data = new byte[LENGTH];
    byteBuffer = ByteBuffer.wrap(data);
    byteBuffer.order(DavisStatics.BYTE_ORDER);
    this.davisApsColumnListener = davisApsColumnListener;
  }

  @Override
  public void aps(DavisApsEvent davisApsEvent) {
    if (davisApsEvent.y == 0) {
      byteBuffer.position(0);
      byteBuffer.putInt(davisApsEvent.time); // prepend time
    }
    // ---
    // subsequent check should not be necessary
    // however, raw data of jaer was observed to contain gaps due to lag/delay
    if (byteBuffer.position() < LENGTH)
      byteBuffer.put(davisApsEvent.grayscale());
    // byteBuffer.put(4 + davisApsEvent.y, davisApsEvent.grayscale());
    // ---
    if (davisApsEvent.y == LAST_Y) { // last
      byteBuffer.position(0);
      davisApsColumnListener.column(davisApsEvent.x, byteBuffer);
    }
  }
}
