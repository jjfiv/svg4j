package com.github.jjfiv.svg4j;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.Map;

/**
 * @author jfoley
 */
public class SVGWriter {

  static XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

  private static class XMLStreamWriterCloseable implements Closeable {
    XMLStreamWriter writer;
    public XMLStreamWriterCloseable(XMLStreamWriter xmlWriter) {
      this.writer = xmlWriter;
    }

    @Override
    public void close() throws IOException {
      try {
        writer.close();
      } catch (XMLStreamException e) {
        throw new IOException(e);
      }
    }

    public XMLStreamWriter get() {
      return writer;
    }
  }

  public static void write(SVGNode svg, OutputStream output) throws IOException {
    try (XMLStreamWriterCloseable xmlswc = new XMLStreamWriterCloseable(xmlOutputFactory.createXMLStreamWriter(output))) {
      write(svg, xmlswc.get());

    } catch (XMLStreamException e) {
      throw new IOException(e);
    }
  }

  static void write(SVGNode svg, XMLStreamWriter writer) throws XMLStreamException {
    writer.writeStartElement(svg.kind);
    // add attributes if any
    for (Map.Entry<String, String> kv : svg.attrs.entrySet()) {
      writer.writeAttribute(kv.getKey(), kv.getValue());
    }
    // add children if any
    for (SVGNode child : svg.children) {
      write(child, writer);
    }
    writer.writeEndElement();
  }
}
