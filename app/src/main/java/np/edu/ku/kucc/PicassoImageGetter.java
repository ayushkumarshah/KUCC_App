package np.edu.ku.kucc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import np.edu.ku.kucc.R;

public class PicassoImageGetter implements Html.ImageGetter {

    private TextView textView = null;
    private Context context;
    private String size;

    public PicassoImageGetter(TextView target, Context context,String size) {
        textView = target;
        this.context =  context;
        this.size=size;
    }

    @Override
    public Drawable getDrawable(String source) {
        BitmapDrawablePlaceHolder drawable = new BitmapDrawablePlaceHolder();
        if (size.equals("fixed"))
        {
            Picasso.with(context)
                    .load(source)
                    .placeholder(R.drawable.logo)
                    .resize(1200,400)
                    .centerInside()
                    .into(drawable);
        }
        else
        {
            Picasso.with(context)
                    .load(source)
                    .placeholder(R.drawable.logo)
                    .into(drawable);
        }

        return drawable;
    }

    private class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

        protected Drawable drawable;

        @Override
        public void draw(final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);
            setBounds(0, 0, width, height);
            if (textView != null) {
                textView.setText(textView.getText());
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            setDrawable(new BitmapDrawable(context.getResources(), bitmap));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }

    }
}