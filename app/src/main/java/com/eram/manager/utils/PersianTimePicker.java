package com.eram.manager.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendarUtils;
import com.eram.manager.R;

import java.util.Calendar;
import java.util.Date;

public class PersianTimePicker extends LinearLayout {

    private OnTimeChangedListener mListener;
    private NumberPicker hourNumberPicker;
    private NumberPicker minutesNumberPicker;
    Calendar c = Calendar.getInstance();
    private int minhour;
    private int maxhour;
    private int hourRange;

    public PersianTimePicker(Context context) {
        this(context, null, -1);
    }

    public PersianTimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PersianTimePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sl_persian_time_picker, this);
        hourNumberPicker = (NumberPicker) view.findViewById(R.id.hourNumberPicker);
        minutesNumberPicker = (NumberPicker) view.findViewById(R.id.minutesNumberPicker);
        // PersianCalendar pCalendar = new PersianCalendar();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersianTimePicker, 0, 0);
        hourRange = a.getInteger(R.styleable.PersianDatePicker_yearRange, 24);


        hourNumberPicker.setMinValue(0);
        hourNumberPicker.setMaxValue(24);
        hourNumberPicker.setOnValueChangedListener(timeChangeListener);
        minutesNumberPicker.setMinValue(0);
        minutesNumberPicker.setMaxValue(60);
        minutesNumberPicker.setOnValueChangedListener(timeChangeListener);

        hourNumberPicker.setValue(c.get(Calendar.HOUR_OF_DAY));
        minutesNumberPicker.setValue(c.get(Calendar.MINUTE));

        a.recycle();
    }

    public String  getDisplayTime() {
        return hourNumberPicker.getValue()+":"+ minutesNumberPicker.getValue();
    }

    NumberPicker.OnValueChangeListener timeChangeListener = new NumberPicker.OnValueChangeListener() {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            int hour = hourNumberPicker.getValue();
            int minutes = minutesNumberPicker.getValue();

            if (mListener != null) {
                mListener.onTimeChanged(hour, minutes);
            }
        }
    };

    public String getDisplayTimeWithOutCharacter() {
        String hour = String.valueOf(hourNumberPicker.getValue());
        String minute = String.valueOf(minutesNumberPicker.getValue());
        if(hour.length()<2)
           hour = "0".concat(hour);
        if(minute.length()<2)
            minute="0".concat(minute);

        return hour.concat(minute);
    }

    public interface OnTimeChangedListener {
        void onTimeChanged(int hour, int minutes);
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        mListener = onTimeChangedListener;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // begin boilerplate code so parent classes can restore state
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        // end

        hourNumberPicker.setMinValue(minhour);
        minutesNumberPicker.setMaxValue(maxhour);

        hourNumberPicker.setValue(c.get(Calendar.HOUR_OF_DAY));
        minutesNumberPicker.setValue(c.get(Calendar.MINUTE));
    }


    static class SavedState extends BaseSavedState {
        long datetime;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.datetime = in.readLong();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeLong(this.datetime);
        }

        // required field that makes Parcelables from a Parcel
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

}
