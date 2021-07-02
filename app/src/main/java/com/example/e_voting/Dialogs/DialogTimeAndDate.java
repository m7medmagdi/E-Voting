package com.example.e_voting.Dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.e_voting.Model.MessageModel;
import com.example.e_voting.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DialogTimeAndDate extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView et_date, et_time;
    ImageView img_date;
    private DialogListenerTimeDate dialogListenerTimeDate;
    String date;
    private DatabaseReference newsMessages;
    FirebaseDatabase database;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);
        builder.setView(view)
                .setTitle("اختر وقت نهاية تفعيل مرحلة الترشح")
                .setPositiveButton("تفعيل", (dialogInterface, i) -> { });

        et_date = view.findViewById(R.id.et_date);
        et_time = view.findViewById(R.id.et_time);
        img_date = view.findViewById(R.id.img_date);


        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                c.getTimeInMillis();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),DialogTimeAndDate.this::onDateSet,
                       year, month, day);
                datePickerDialog.show();
            }
        });



        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListenerTimeDate = (DialogTimeAndDate.DialogListenerTimeDate) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement DialogListener");
        }
    }


    public interface DialogListenerTimeDate{
        void fromDialogToActiveNomination(int year, int month, int day, int hour, int minute);
    }


    public void onResume()
    {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_date.length()==0) {
                        et_date.setError("ادخل التاريخ");
                    }else if (et_time.length()==0){
                        et_time.setError("ادخل الوقت");
                    }else {

                        String dateNomination = et_date.getText().toString();
                        dialogListenerTimeDate.fromDialogToActiveNomination(yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal);

                        database = FirebaseDatabase.getInstance();
                        final DatabaseReference newsMessages = database.getReference("NewsMessages").push();
                        MessageModel messageModel = new MessageModel();
                        messageModel.setMessage("تم فتح مرحلة الترشح الترشح تاريخ النهاية "+dateNomination+" يمكنك الترشح قبل انتهاء الفترة ");
                        messageModel.setId(newsMessages.getKey());
                        newsMessages.setValue(messageModel);

                        d.dismiss();
                    }
                }
            });
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month , int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),DialogTimeAndDate.this
                ,hour,minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal =i;
        minuteFinal = i1;

        int month = monthFinal+1;
        et_date.setText(yearFinal + "/" + month + "/" + dayFinal);
        et_time.setText(hourFinal + "/" + minuteFinal);
    }
}
