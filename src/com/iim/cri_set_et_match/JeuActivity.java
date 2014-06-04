package com.iim.cri_set_et_match;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;


public class JeuActivity extends Activity implements OnClickListener{
	AudioManager soundManager;
    int Volume;
    SoundPool soundPool;
    SparseIntArray soundPoolMap;
    
    
    final int FEDERER = 1;
    final int AZARENKA = 2;
    final int MURRAY = 3;
    final int HINGIS = 4;
    final int NADAL = 5;
    final int SCHIAVONE = 6;
    final int SHARAPOVA = 7;
    final int TSONGA = 8;
    final int SERENA = 9;
    
    final int QUARANTE = 10;
    final int JEUSETMATCH = 11;
    final int FAUTE = 12;

    final int BEAT1 = 13;
    final int BEAT2 = 14;
    
    MediaPlayer mp;
    
    boolean play1 = false;
    boolean play2 = false;

    RelativeLayout rl;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeu);

		// cris joueurs
        this.findViewById(R.id.federer).setOnClickListener(this);
        this.findViewById(R.id.azarenka).setOnClickListener(this);
        this.findViewById(R.id.hingis).setOnClickListener(this);
        this.findViewById(R.id.murray).setOnClickListener(this);
        this.findViewById(R.id.nadal).setOnClickListener(this);
        this.findViewById(R.id.schiavone).setOnClickListener(this);
        this.findViewById(R.id.serena).setOnClickListener(this);
        this.findViewById(R.id.sharapova).setOnClickListener(this);
        this.findViewById(R.id.tsonga).setOnClickListener(this);
        
        // Autres sons
        this.findViewById(R.id.quarante).setOnClickListener(this);
        this.findViewById(R.id.jeusetmatch).setOnClickListener(this);
        this.findViewById(R.id.faute).setOnClickListener(this);
        
        // beats
        this.findViewById(R.id.beat1).setOnClickListener(this);
        this.findViewById(R.id.beat2).setOnClickListener(this);
        
        soundManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        Volume =  soundManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(14, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new SparseIntArray();
        soundPoolMap.put(FEDERER, soundPool.load(this, R.raw.federer, 1));
        soundPoolMap.put(AZARENKA, soundPool.load(this, R.raw.azarenka, 1));
        soundPoolMap.put(HINGIS, soundPool.load(this, R.raw.hingins, 1));
        soundPoolMap.put(MURRAY, soundPool.load(this, R.raw.murray, 1));
        soundPoolMap.put(NADAL, soundPool.load(this, R.raw.nadal, 1));
        soundPoolMap.put(SCHIAVONE, soundPool.load(this, R.raw.schiavone, 1));
        soundPoolMap.put(SERENA, soundPool.load(this, R.raw.serena_williams, 1));
        soundPoolMap.put(SHARAPOVA, soundPool.load(this, R.raw.sharapova, 1));
        soundPoolMap.put(TSONGA, soundPool.load(this, R.raw.tsonga, 1));
        
        
        soundPoolMap.put(QUARANTE, soundPool.load(this, R.raw.quarante_zero, 1));
        soundPoolMap.put(FAUTE, soundPool.load(this, R.raw.faute, 1));
        soundPoolMap.put(JEUSETMATCH, soundPool.load(this, R.raw.jeusetetmatch, 1));
        
	}

	@Override
	protected void onStart(){
		super.onStart();
	}

	public void onPause(){
		stopMp();
		super.onPause();
	}

    @Override

    public void onClick(View v) {
            switch(v.getId()){
            case R.id.federer:
                soundPool.play(soundPoolMap.get(FEDERER), Volume, Volume, 1, 0, 1f);
                break;

            case R.id.azarenka:
                soundPool.play(soundPoolMap.get(AZARENKA), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.murray:
                soundPool.play(soundPoolMap.get(MURRAY), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.hingis:
                soundPool.play(soundPoolMap.get(HINGIS), Volume, Volume, 1, 0, 1f);
                break;

            case R.id.nadal:
                soundPool.play(soundPoolMap.get(NADAL), Volume, Volume, 1, 0, 1f);
                break;

            case R.id.schiavone:
                soundPool.play(soundPoolMap.get(SCHIAVONE), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.sharapova:
                soundPool.play(soundPoolMap.get(SHARAPOVA), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.tsonga:
                soundPool.play(soundPoolMap.get(TSONGA), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.serena:
                soundPool.play(soundPoolMap.get(SERENA), Volume, Volume, 1, 0, 1f);
                break;
                
            case R.id.quarante:
                soundPool.play(soundPoolMap.get(QUARANTE), Volume, Volume, 1, 0, 1f);
                break;
                         
            case R.id.jeusetmatch:
                soundPool.play(soundPoolMap.get(JEUSETMATCH), Volume, Volume, 1, 0, 1f);
                break;
            
            case R.id.faute:
                soundPool.play(soundPoolMap.get(FAUTE), Volume, Volume, 1, 0, 1f);
                break;


            case R.id.beat1:
                stopMp();
                if(play1 == false){
                        try{
                            mp = MediaPlayer.create(this, R.raw.beat1);
                            mp.start();
                            mp.setLooping(true);
                            play1 = true;
                            play2 = false;

                        }catch(Exception e){
                            e.printStackTrace();
                            stopMp();
                        }
                }else{
                    play1 = false;
                }
                break;

                    

            case R.id.beat2:
                stopMp();
                if(play1 == true){
                    stopMp();
                    play1 = false;
                }

                if(play2 == false){
                    try{
                        mp = MediaPlayer.create(this, R.raw.beat2);
                        mp.start();
                        mp.setLooping(true);
                        play2 = true;
                        play1 = false;

                    }catch(Exception e){
                        e.printStackTrace();
                        stopMp();
                    }

                }else{
                    play2 = false;
                }
                break;
            }

    }

    private void stopMp(){
        if(mp != null){
            mp.stop();
            mp = null;
        }
    }
}
