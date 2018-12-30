package com.example.matthew.plugin;

import com.maxwell.speechrecognition.OnSpeechRecognitionListener;
import com.maxwell.speechrecognition.OnSpeechRecognitionPermissionListener;
import com.unity3d.player.UnityPlayer;
import com.maxwell.speechrecognition.SpeechRecognition;

import android.app.Activity;
import android.util.Log;

public class VoiceReco implements OnSpeechRecognitionPermissionListener,OnSpeechRecognitionListener {

    private static VoiceReco INSTANCE = null;

    // other instance variables can be here

    private VoiceReco() {};

    public static VoiceReco getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VoiceReco();
        }
        return(INSTANCE);
    }

    SpeechRecognition speechRecognition;

    public void Init(Activity currActivity){
        Log.d("rec","Init FROM ANDROID");
        speechRecognition = new SpeechRecognition(currActivity);
        speechRecognition.setSpeechRecognitionPermissionListener(this);
        speechRecognition.setSpeechRecognitionListener(this);
    }

    public void StartRecognition(){
        Log.e("rec", "calling start reco...");
        speechRecognition.startSpeechRecognition();
    }

    @Override
    public void onPermissionGranted() {
        //RECORD_AUDIO permission was granted
        Log.e("rec", "permission granted:");
    }
    @Override
    public void onPermissionDenied() {
        //RECORD_AUDIO permission was denied
        Log.e("rec", "permission denied:");
    }

    @Override
    public void OnSpeechRecognitionStarted() {
    }

    @Override
    public void OnSpeechRecognitionStopped() {
    }

    @Override
    public void OnSpeechRecognitionFinalResult(String s) {
        //triggered when SpeechRecognition is done listening.
        //it returns the translated text from the voice input
        Log.e("rec", "GOT RESULT!! " + s);
        UnityPlayer.UnitySendMessage("VoiceController", "OnVoiceResult", s);
    }
    @Override
    public void OnSpeechRecognitionCurrentResult(String s) {
        //this is called multiple times when SpeechRecognition is
        //still listening. It returns each recognized word when the user is still speaking
    }
    @Override
    public void OnSpeechRecognitionError(int i, String s) {
        UnityPlayer.UnitySendMessage("VoiceController", "OnErrorResult", s);
    }
}
