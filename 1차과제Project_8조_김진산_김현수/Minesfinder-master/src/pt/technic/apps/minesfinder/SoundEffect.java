package pt.technic.apps.minesfinder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {
    private static Clip[] clip = new Clip[3];

    private static String[] file = {"src/pt/technic/apps/minesfinder/music/BGM.wav",
            "src/pt/technic/apps/minesfinder/music/ClickS.wav", "src/pt/technic/apps/minesfinder/music/BoomS.wav"};
    //0 : BGM
    //1 : ClickS
    //2 : BoomS
    public static void bgmClip() {
        if (clip[0] == null) {
            try {
                File base = new File(file[0]);
                String basePath = base.getAbsolutePath();
                File audio = new File(basePath);
                AudioInputStream ais = AudioSystem.getAudioInputStream(audio);
                clip[0] = AudioSystem.getClip();
                clip[0].open(ais);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        clip[0].start();
        clip[0].loop(clip[0].LOOP_CONTINUOUSLY);
    }


    public static void clickClip() {
        if (clip[1] == null) {
            try {
                File base = new File(file[1]);
                String basePath = base.getAbsolutePath();
                File audio = new File(basePath);
                AudioInputStream ais = AudioSystem.getAudioInputStream(audio);
                clip[1] = AudioSystem.getClip();
                clip[1].open(ais);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clip[1].start();
    }

    public static void boomClip() {
        if (clip[2] == null) {
            try {
                File base = new File(file[2]);
                String basePath = base.getAbsolutePath();
                File audio = new File(basePath);
                AudioInputStream ais = AudioSystem.getAudioInputStream(audio);
                clip[2] = AudioSystem.getClip();
                clip[2].open(ais);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clip[2].start();
    }
}
