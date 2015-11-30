package utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.romain.myapplication.R;

import java.util.jar.Attributes;

/**
 * Created by romain on 30/11/2015.
 */
public class TimeView extends View {
    public Paint circlePaint;
    public Paint circleCenterPaint;
    private boolean processed = false;

    public void setTime(String time) {
        time = time.split(" ")[1];
        String[] splittedTime = time.split(":");
        hour = Integer.parseInt(splittedTime[0]);
        minute = Integer.parseInt(splittedTime[1]);
        //invalidate();
    }

    private int hour;
    private int minute;

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.init();
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = height / 2;
        canvas.drawCircle(width / 2, height / 2, radius, circlePaint);
        canvas.drawCircle(width / 2, height / 2, 5, circleCenterPaint);
        //if(!time.equals("") && !processed) {
            drawFromTime(canvas);
        //}
    }

    private void init() {
        Resources resources = getResources();
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(resources.getColor(R.color.colorPrimaryDark));

        circleCenterPaint = new Paint();
        circleCenterPaint.setStyle(Paint.Style.FILL);
        circleCenterPaint.setStrokeWidth(3);
        circleCenterPaint.setColor(resources.getColor(R.color.colorPrimary));
    }

    private void drawFromTime(Canvas canvas) {
        canvas.save();
        int x = getWidth() / 2;
        int y = getHeight()/2;
        canvas.rotate(((360/12) * (hour%12)),x,y);
        canvas.drawLine(x,y,x, y -30,circleCenterPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(((360/60) * (minute%60)),x,y);
        canvas.drawLine(x,y,x, y -50,circleCenterPaint);
        canvas.restore();
    }
}
