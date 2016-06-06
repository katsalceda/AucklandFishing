package rktechltd.aucklandfishing.utilities;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * This class helps in converting the images to byte array, ready to be saved to the database
 * @version 16/05/2016
 * @author Romelyn Ungab
 * @author Katrina Salceda
 */
public class ImageHelper {
    public static byte[] convertImage(String imagePath) {
        byte[] data ;

        Bitmap src = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.PNG, 100, baos);
        data = baos.toByteArray();
        return data;
    }

    public static byte[] convertImage(Bitmap bitmap){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] img=bos.toByteArray();
        return img;
    }
    public static byte[] convertImage(Drawable d) {
        byte[] data ;
        Bitmap src =((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.PNG, 100, baos);
        data = baos.toByteArray();
        return data;
    }



    public static Bitmap convertImage(byte[] image) {
        Bitmap bmp=BitmapFactory.decodeByteArray(image,0,image.length);
        return bmp;
    }

}
