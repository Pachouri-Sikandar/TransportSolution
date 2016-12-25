package com.pachouri.transportsolution.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.widgets.AppButton;
import com.pachouri.transportsolution.widgets.AppTextView;

/**
 * Created by tt-riyaz on 25/12/16.
 */
public class DialogUtil {

    public interface IImageSelectionDialog{
        void onClickCamera();
        void onClickGallery();
    }

    public interface IDialog {
        void onSubmitClick();
        void onCancelClick();
    }


    public static void showImageSelectionDialog(Context context, final IImageSelectionDialog iImageSelectionDialog){
        final Dialog dialog = new Dialog(context,R.style.dialog_theme);
        dialog.setContentView(R.layout.layout_dialog_select_photo);

        AppButton btnCamera = (AppButton) dialog.findViewById(R.id.btn_take_photo);
        AppButton btnGallery = (AppButton) dialog.findViewById(R.id.btn_choose_library);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iImageSelectionDialog.onClickCamera();
                dialog.dismiss();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iImageSelectionDialog.onClickGallery();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showDialog(Context context,CharSequence displayText, String yesBtnText, String noBtnText,
                                  final boolean shouldShowSingleButtons, final IDialog iDialog) {
        final Dialog dialog = new Dialog(context, R.style.dialog_theme);
        dialog.setContentView(R.layout.layout_confirmation_dialog);

        Button btnNo = (Button) dialog.findViewById(R.id.btn_no);
        Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);

        btnYes.setText(yesBtnText);
        btnNo.setText(noBtnText);
        AppTextView txtDialogMsg = (AppTextView) dialog.findViewById(R.id.txt_dialog_msg);
        txtDialogMsg.setText(displayText);

        if (!shouldShowSingleButtons) {
            //Two buttons 'Yes' and 'No'
            btnYes.setVisibility(View.VISIBLE);
            btnNo.setVisibility(View.VISIBLE);
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    iDialog.onCancelClick();
                }
            });

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iDialog.onSubmitClick();
                    dialog.dismiss();
                }
            });
        } else {
            //One button 'Ok'
            btnYes.setVisibility(View.VISIBLE);
            btnYes.setText("Ok");
            btnNo.setVisibility(View.GONE);

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(iDialog != null)
                        iDialog.onSubmitClick();
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
    }
}
