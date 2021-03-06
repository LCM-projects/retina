// code by jph
package ch.ethz.idsc.retina.dev.davis.app;

import java.awt.image.BufferedImage;

import ch.ethz.idsc.retina.core.ColumnTimedImageListener;
import ch.ethz.idsc.retina.dev.davis.io.DavisExportControl;
import ch.ethz.idsc.retina.util.GlobalAssert;

/** export control that is enabled from the 2nd image onwards */
public class FirstImageTriggerExportControl implements DavisExportControl, ColumnTimedImageListener {
  private int frames = 0;
  private int time_offset = 0;

  @Override
  public void image(int[] time, BufferedImage bufferedImage, boolean isComplete) {
    if (1 == frames) {
      GlobalAssert.that(!isActive());
      time_offset = time[0];
      System.out.println("enabled at " + time_offset);
    }
    ++frames;
  }

  @Override
  public boolean isActive() {
    return 2 < frames;
  }

  @Override
  public int mapTime(int time) {
    int mapped = time - time_offset;
    if (mapped <= 0)
      System.err.println("mapped=" + mapped);
    // GlobalAssert.that(0 <= mapped);
    return mapped;
  }
}
