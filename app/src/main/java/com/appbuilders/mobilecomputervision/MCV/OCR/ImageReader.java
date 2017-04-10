package com.appbuilders.mobilecomputervision.MCV.OCR;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import android.widget.ImageView;

import com.appbuilders.mobilecomputervision.MCV.MCV;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

/**
 * Created by Erick on 08/04/2017.
 */
public abstract class ImageReader extends MCV {

    protected Bitmap image;
    protected TextRecognizer recognizer;
    StringBuilder result = null;

    public ImageReader(Context context) {

        super(context);
        this.buildRecognizer();
    }

    private void buildRecognizer() {

        this.recognizer = new TextRecognizer.Builder(this.context.getApplicationContext()).build();
    }

    public ImageReader setImageBitmap(Bitmap bitmap) {

        this.image = bitmap;
        return this;
    }

    public ImageReader setImageDrawable(int drawable) {

        this.image = BitmapFactory.decodeResource(this.context.getResources(), drawable);
        return this;

    }

    public ImageReader setImageFromImageView(ImageView view) {

        BitmapDrawable bit = (BitmapDrawable) view.getDrawable();
        Bitmap bitmap = bit.getBitmap();
        this.setImageBitmap(bitmap);
        return this;

    }

    public ImageReader process() {

        if ( this.recognizer != null && this.recognizer.isOperational() ) {

            /** We call the method to notify the process it's starting */
            this.onProcessStart();

            Frame frame = new Frame.Builder().setBitmap(this.image).build();
            SparseArray<TextBlock> items = recognizer.detect(frame);
            StringBuilder string = new StringBuilder();

            for ( int i = 0; i < items.size(); i++ ) {
                TextBlock item = items.valueAt(i);
                string.append(item.getValue());
                string.append("\n");
            }

            /** We call the method to notify the process it's ending */
            this.onProcessEnd();
            /** We call the method to send the result into the abstract method */
            this.processText(string);
            /** We ave the results */
            this.result = string;
            /** We return this */
            return this;
        }

        return null;
    }

    public StringBuilder getResult() {
        return this.result;
    }

    public String getResultString() {

        return String.valueOf(this.result);
    }

    public abstract void onProcessStart();

    public abstract void onProcessEnd();

    public abstract void processText(StringBuilder result);
}
