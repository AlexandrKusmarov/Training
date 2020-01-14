package main.java.storage.initor;

import main.java.storage.initor.fileinitor.FileStorageInitor;
import main.java.storage.initor.fileinitor.InMemoryStorageInitor;
import main.java.storage.initor.fileinitor.DomXmlStorageInitor;
import main.java.storage.initor.fileinitor.SaxXmlStorageInitor;

public final class StorageInitorFactory {

    private StorageInitorFactory() {

    }

    public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
        switch (initStorageType) {

            case MEMORY: {
                return new InMemoryStorageInitor();
            }
            case TEXT_FILE: {
                return new FileStorageInitor();
            }
            case XML_DOM_FILE:{
                return new DomXmlStorageInitor();
            }
            case XML_SAX_FILE:{
                return new SaxXmlStorageInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
