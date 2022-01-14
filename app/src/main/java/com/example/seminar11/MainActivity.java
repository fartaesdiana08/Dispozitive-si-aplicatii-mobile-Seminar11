package com.example.seminar11;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout=findViewById(R.id.linearLayout);

        float[] val={12.5f,3.6f,7.88f,10,35.6f,50,72.3f};

        linearLayout.addView(new PieChartView(this,calculatePieChartData(val)));

    }

    private float[] calculatePieChartData(float[] values){

        float total=0;
        float[] newValues=new float[values.length];
        for(int i=0; i<values.length; i++){
            total+=values[i];
        }

        for(int i=0; i<values.length; i++){
            newValues[i]=360*(values[i]/total);
        }

        return newValues;
    }

    public class PieChartView extends View {

        private float[] chartValues;
        private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] colors={Color.YELLOW, Color.CYAN, Color.RED, Color.GREEN, Color.MAGENTA,Color.BLUE,
            Color.GRAY};
        private RectF canvasSpace=new RectF(10,10,500,500);
        private int temp=0;

        public PieChartView(Context context, float[] values) {
            super(context);
            chartValues=new float[values.length];
            for(int i=0; i<values.length; i++){
                chartValues[i]=values[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for(int i=0; i<chartValues.length; i++){
                paint.setColor(colors[i]);
                if(i==0){
                    canvas.drawArc(canvasSpace,0, chartValues[i],true, paint);
                }
                else{
                    temp+=chartValues[i-1];
                    canvas.drawArc(canvasSpace,temp,chartValues[i],true,paint);
                }
            }
        }
    }
}