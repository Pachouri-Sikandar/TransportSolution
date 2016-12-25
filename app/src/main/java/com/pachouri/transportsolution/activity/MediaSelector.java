package com.pachouri.transportsolution.activity;

/**
 * Created by tt-riyaz on 25/12/16.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.pachouri.transportsolution.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MediaSelector {

    private static final int UPLOAD_VIDEO_REQUEST = 242;
    private static final int TAKE_VIDEO_REQUEST = 562;


    public static void uploadPhotoActivity(final Activity activity){
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

        if(!Dexter.isRequestOngoing())
            Dexter.checkPermissionsOnSameThread(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {

                    if(report.areAllPermissionsGranted())
                        EasyImage.openDocuments(activity, 0);
                    else if(report.isAnyPermissionPermanentlyDenied())
                        DialogUtil.showDialog(activity,activity.getString(R
                                .string.cancel_permanently), "", "", true, new DialogUtil.IDialog() {
                            @Override
                            public void onSubmitClick() {

                            }

                            @Override
                            public void onCancelClick() {

                            }
                        });
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                /*CommonUtils.displayToast(activity,activity.getString(R
                        .string.denied));*/
                }
            },permissions);
    }

    public static void takePhotoActivity(final Activity activity){
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};

        if(!Dexter.isRequestOngoing())
            Dexter.checkPermissionsOnSameThread(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if(report.areAllPermissionsGranted())
                        EasyImage.openCamera(activity, 0);
                    else if(report.isAnyPermissionPermanentlyDenied())
                        DialogUtil.showDialog(activity,activity.getString(R
                                .string.cancel_permanently), "", "", true, new DialogUtil.IDialog() {
                            @Override
                            public void onSubmitClick() {

                            }

                            @Override
                            public void onCancelClick() {

                            }
                        });
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            },permissions);
    }

    public static void handleActivityResult(int requestCode, int resultCode, Intent data, final boolean shouldCrop, final Activity activity, final IMediaSelector iMediaSelector){

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri resultUri = result.getUri();
                File imageFile = new File(resultUri.getPath());
                iMediaSelector.croppedImageFile(imageFile);
                iMediaSelector.croppedImageUri(resultUri.getPath().toString());
            }
        }
        else {
            EasyImage.handleActivityResult(requestCode, resultCode, data, activity, new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //CommonUtils.showToast(fragment.getActivity(), fragment.getString(R.string.error_on_picking_image));
                    iMediaSelector.imagePickerError("Error");
                }

                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    Uri imageUri = Uri.fromFile(imageFile);
                    iMediaSelector.originalImageFile(imageFile);
                    if(shouldCrop)
                    {
                        cropImageActivity(activity, imageUri);
                    }
                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(activity);
                        if (photoFile != null) photoFile.delete();
                        iMediaSelector.onMediaSelectionCancel();
                    }
                }
            });
        }
    }


    public static void cropImageActivity(Activity activity,Uri imageUri){
        CropImage.activity(imageUri)
                .setAutoZoomEnabled(true)
                .setRotationDegrees(90)
                .setAspectRatio(1, 1)
                .setFixAspectRatio(true)
                .setActivityMenuIconColor(R.color.colorPrimary)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setGuidelines(CropImageView.Guidelines.OFF)
                .start(activity);
    }

    public interface IMediaSelector {
        void originalImageFile(File originalImage);
        void imagePickerError(String message);
        void croppedImageFile(File croppedImage);
        void croppedImageUri(String imagePath);
        void onMediaSelectionCancel();
    }

}
