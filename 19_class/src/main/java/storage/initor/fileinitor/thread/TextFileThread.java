package storage.initor.fileinitor.thread;

import java.io.File;

public class TextFileThread implements Runnable {

    private File threadFile;
    private Thread thread;


    public TextFileThread(File file) {
        threadFile = file;
        thread = new Thread(this);
    }

    @Override
    public void run() {

    }
}
