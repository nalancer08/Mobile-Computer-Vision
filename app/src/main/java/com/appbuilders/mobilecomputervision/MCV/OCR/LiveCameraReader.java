package com.appbuilders.mobilecomputervision.MCV.OCR;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.appbuilders.mobilecomputervision.MCV.MCV;
import com.appbuilders.mobilecomputervision.MCV.OCR.activity.OcrCaptureActivity;

/**
 * Created by Erick on 09/04/2017.
 */
public class LiveCameraReader extends MCV {

    private static final int RC_OCR_CAPTURE = 9003;
    private static final int GOOGLE_DEFAULT_MODE = 1;
    private static final int APPBUILDERS_CUSTOM_MODE = 2;
    private static final int CUSTOM_MODE = 3;

    private AppCompatActivity activity = null;
    private boolean autoFocus = true;
    private boolean flash = false;
    private int mode = 1;

    public LiveCameraReader(AppCompatActivity activity) {

        super(activity.getApplicationContext());
        this.activity = activity;
    }

    public LiveCameraReader setAutoFocus(boolean focus) {
        this.autoFocus = focus;
        return this;
    }

    public boolean getAutoFocus() {
        return this.autoFocus;
    }

    public LiveCameraReader setFlash(boolean flash) {
        this.flash = flash;
        return this;
    }

    public boolean getFlash() {
        return this.flash;
    }

    public LiveCameraReader setMode(int mode) {
        this.mode = mode;
        return this;
    }

    public LiveCameraReader start() {

        switch (this.mode) {

            case GOOGLE_DEFAULT_MODE:

                Intent intent = new Intent(this.activity, OcrCaptureActivity.class);
                intent.putExtra(OcrCaptureActivity.AutoFocus, this.autoFocus);
                intent.putExtra(OcrCaptureActivity.UseFlash, this.flash);
                this.activity.startActivityForResult(intent, RC_OCR_CAPTURE);

            break;

            case APPBUILDERS_CUSTOM_MODE:
                /** APPBBUILDERS CUSTOM MODE RIGHT HERE **/
            break;

            case CUSTOM_MODE:
                /** In this case, you have to check if passs the necesaty interfaces to do this **/
            break;

            default:
                break;
        }

        return this;
    }

}
