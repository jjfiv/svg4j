package com.github.jjfiv.svg4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author jfoley
*/
public class SVGNode {
  public String kind;
  public Map<String,String> attrs;
  public List<SVGNode> children;

  protected SVGNode(String kind) {
    this.kind = kind;
    this.attrs = new HashMap<>();
    this.children = new ArrayList<>();
  }

  public SVGNode attr(String key, String val) {
    this.attrs.put(key, val);
    return this;
  }
  public SVGNode attr(String key, Number val) {
    assert(val != null);
    this.attrs.put(key, val.toString());
    return this;
  }

  public SVGNode attrs(Object... kvs) {
    for (int i = 0; i < kvs.length; i+=2) {
      String k = kvs[i].toString();
      String v = kvs[i+1].toString();
      this.attrs.put(k,v);
    }
    return this;
  }

  public SVGNode xywh(Number x, Number y, Number w, Number h) {
    if(kind.equals("circle")) throw new RuntimeException("Shouldn't set xywh on a circle!");
    this.attrs.put("x", x.toString());
    this.attrs.put("y", y.toString());
    this.attrs.put("width", w.toString());
    this.attrs.put("height", h.toString());
    return this;
  }

  public double getWidth() {
    return Double.parseDouble(attrs.get("width"));
  }
  public double getHeight() {
    return Double.parseDouble(attrs.get("width"));
  }

  public SVGNode fill(String color) {
    return attr("fill", color);
  }
  public SVGNode stroke(String color, int width) {
    return attr("stroke", color).attr("stroke-width", width);
  }

  public void addChild(SVGNode child) {
    this.children.add(child);
  }

  public SVGNode addChild(String kind) {
    SVGNode child = of(kind);
    this.children.add(child);
    return child;
  }

  public static SVGNode of(String kind) {
    return new SVGNode(kind);
  }

  public static SVGNode rect(Number x, Number y, Number w, Number h) {
    return of("rect").xywh(x,y,w,h);
  }
  public static SVGNode line(Number x1, Number y1, Number x2, Number y2) {
    return of("line").attrs(
        "x1", x1, "y1", y1,
        "x2", x2, "y2", y2
    );
  }

  public SVGNode translate(Number tx, Number ty) {
    return attr("transform", "translate("+tx+","+ty+")");
  }
  public SVGNode scale(Number sx, Number sy) {
    return attr("transform", "scale("+sx.toString()+","+sy.toString()+")");
  }

  public SVGNode printAttrs() {
    System.out.println(attrs);
    return this;
  }
}
