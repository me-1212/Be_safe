package com.example.be_safe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.location.FusedLocationProviderClient;

public class DeleteDialog extends AppCompatDialogFragment {

    private EditText name;
    private DeleteDialogListener ddl;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE =100;

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_delete, null);
        builder.setView(view)
                .setTitle("Delete Box")
                .setMessage("Enter the name you want to remove: ")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name_del = name.getText().toString();
                        ddl.applyTexts(name_del);
                    }
                });
        name = view.findViewById(R.id.edit1);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            ddl = (DeleteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DeleteDialogListener");
        }
    }

    public interface DeleteDialogListener {
        void applyTexts(String name);
    }

}
