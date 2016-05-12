package com.alexkang.Raahat;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MessageBox {

    private String sender;
    private String message;
    private Bitmap image;
    private Date time;

    private boolean self;
    private boolean isImage;

    public MessageBox(String sender, String message, Date time, boolean self) {
        this.sender = sender;
        this.message = message;
        this.time = time;
        this.self = self;
        this.isImage = false;
    }
    public void saveImage(Bitmap bitmap)
    {



        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/raahat_images");
        if(!myDir.isDirectory()){
            myDir.mkdirs();
        }

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void save2(Bitmap bitmap)
    {
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File(Environment.getExternalStorageDirectory().toString(), fname);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("JS", "File name -> " + file.getAbsolutePath());
        //Here 'bmp' is of type Bitmap
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
        try {
            os.flush();
            os.getFD().sync();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        MediaScannerConnection.scanFile(this,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });*/
    }

    public MessageBox(String sender, Bitmap image, Date time, boolean self) {
        this(sender, "", time, self);
        this.image = image;
        //save2(image);
        this.isImage = true;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTime() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm");
        return dateFormatter.format(time);
    }

    public boolean isSelf() {
        return self;
    }

    public boolean isImage() {
        return isImage;
    }

}
