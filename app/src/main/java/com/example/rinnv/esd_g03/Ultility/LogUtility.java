package com.example.rinnv.esd_g03.Ultility;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by rinnv on 5/6/2017.
 */

public class LogUtility {
    public void writeLog(String data, Context context) {
        try {
            final File path =
                    Environment.getExternalStoragePublicDirectory
                            (
                                    Environment.DIRECTORY_DOWNLOADS + "/YourFolder/"
                            );
            if (!path.exists()) {
                path.mkdirs();
            }

            final File file = new File(path, "config.txt");
            try {

                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fOut = new FileOutputStream(file,true);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.append("\n");
                myOutWriter.append(data);

                myOutWriter.close();

                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                throw e;
            }

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
