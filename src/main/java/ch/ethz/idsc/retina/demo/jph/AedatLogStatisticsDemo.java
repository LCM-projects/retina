// code by jph
package ch.ethz.idsc.retina.demo.jph;

import ch.ethz.idsc.retina.dvs.app.AedatLogStatistics;

enum AedatLogStatisticsDemo {
  ;
  public static void main(String[] args) throws Exception {
    AedatLogStatistics.of(Datahaki.LOG_03.file);
  }
}
