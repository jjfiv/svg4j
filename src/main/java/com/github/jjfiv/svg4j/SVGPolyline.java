package com.github.jjfiv.svg4j;

import java.awt.geom.Point2D;

/**
 * @author jfoley
 */
public class SVGPolyline {
  public static SVGNode create(Iterable<? extends Point2D> points) {
    StringBuilder pointsAttr = new StringBuilder();

    for (Point2D point : points) {
      pointsAttr.append(point.getX()).append(",").append(point.getY()).append(' ');
    }

    return SVGNode.of("polyline").attr("points", pointsAttr.toString().trim()).attr("fill", "none");
  }
}
