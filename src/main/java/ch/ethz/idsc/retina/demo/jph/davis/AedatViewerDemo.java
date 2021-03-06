// code by jph
package ch.ethz.idsc.retina.demo.jph.davis;

import ch.ethz.idsc.retina.core.StartAndStoppable;
import ch.ethz.idsc.retina.dev.davis.DavisDecoder;
import ch.ethz.idsc.retina.dev.davis._240c.Davis240c;
import ch.ethz.idsc.retina.dev.davis.app.DavisEventViewer;
import ch.ethz.idsc.retina.dev.davis.io.AedatFileSupplier;

/** playback of aedat log file and visualization of content.
 * data processing is restricted to dvs event accumulation */
enum AedatViewerDemo {
  ;
  public static void main(String[] args) throws Exception {
    DavisDecoder davisDecoder = Davis240c.INSTANCE.createDecoder();
    StartAndStoppable davisEventProvider = new AedatFileSupplier(Aedat.LOG_04.file, davisDecoder);
    DavisEventViewer.of(davisEventProvider, davisDecoder, Davis240c.INSTANCE, 1.0);
  }
}
