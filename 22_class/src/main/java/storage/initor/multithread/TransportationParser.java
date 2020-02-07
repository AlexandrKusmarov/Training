package storage.initor.multithread;

import storage.initor.fileinitor.BaseFileInitor.ParsedTransportation;
import common.solutions.utils.FileUtils;
import common.solutions.utils.xml.sax.XmlSaxUtils;
import storage.initor.fileinitor.xml.sax.SaxHandler;
import storage.initor.fileinitor.xml.sax.XmlSaxFileDataInitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import javax.xml.parsers.SAXParser;

public class TransportationParser implements Runnable {

  private static final String FILE = "D:\\JAVA\\EPAM_SPB\\Training\\initdata\\xmldata.xml";

  private volatile boolean hasError = false;

  private List<ParsedTransportation> transportations;

  @Override
  public void run() {
    File file = null;
    try {
      file = getFileWithInitData();
      List<ParsedTransportation> transportations = parseCargosFromFile(file);
      setTransportations(transportations);
    } catch (Exception e) {
      e.printStackTrace();
      hasError = true;
    } finally {
      if (file != null) {
        file.delete();
      }
    }
  }

  private List<ParsedTransportation> parseCargosFromFile(File file) throws Exception {
    SAXParser parser = XmlSaxUtils.getParser();
    SaxHandler saxHandler = new SaxHandler();
    parser.parse(file, saxHandler);
    return saxHandler.getTransportations();
  }

  private File getFileWithInitData() throws IOException {
    return FileUtils
            .createFileFromResource(Paths.get(FILE), "init-data", "lesson12");
  }

  public boolean isHasError() {
    return hasError;
  }

  private synchronized void setTransportations(List<ParsedTransportation> transportations) {
    this.transportations = transportations;
  }

  public synchronized List<ParsedTransportation> getTransportations() {
    return this.transportations;
  }
}
