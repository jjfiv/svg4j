package com.github.jjfiv.svg4j;

import java.awt.geom.Point2D;

/**
 * @author jfoley
 */
public class SVGPoint extends Point2D {
  Number x;
  Number y;

  private SVGPoint(Number x, Number y) {
    assert(x != null);
    assert(y != null);
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return x.doubleValue();
  }

  @Override
  public double getY() {
    return y.doubleValue();
  }

  @Override
  public void setLocation(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /** Turn a point into a circle, for plotting */
  public SVGNode asCircle(Number radius) {
    return SVGNode.of("circle").attr("cx", x).attr("cy", y).attr("r", radius);
  }

  /** Constructor from java.awt.geom classes */
  public static SVGPoint of(Point2D input) {
    return new SVGPoint(input.getX(), input.getY());
  }
  public static SVGPoint of(double x, double y) {
    return new SVGPoint(x,y);
  }
}
