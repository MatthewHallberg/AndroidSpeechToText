package com.example.matthew.plugin;
import com.unity3d.player.UnityPlayer;

public class VoiceBridge  {

    public void StartPlugin(){
        VoiceReco.getInstance().Init(UnityPlayer.currentActivity);
    }

    public void StartSpeaking(){
        VoiceReco.getInstance().StartRecognition();
    }
}
