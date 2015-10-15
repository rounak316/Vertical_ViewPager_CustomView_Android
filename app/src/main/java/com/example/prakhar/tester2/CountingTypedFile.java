package com.example.prakhar.tester2;

import android.os.RecoverySystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import retrofit.mime.TypedFile;

/**
 * Created by Prakhar on 9/28/2015.
 */
public class CountingTypedFile extends TypedFile  {

    private static final int BUFFER_SIZE = 4096;

    private final ProgressListener listener;

    public CountingTypedFile(String mimeType, File file, ProgressListener listener) {
        super(mimeType, file);
        this.listener = listener;
    }

    @Override public void writeTo(OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream in = new FileInputStream(super.file());



        float total = 0;
        try {
            int read;
            while ((read = in.read(buffer)) != -1) {
                total += read;
                this.listener.transferred( (((total ) / (file().length() )) * 100));
                out.write(buffer, 0, read);
            }
        } finally {
            in.close();
        }
    }


}
