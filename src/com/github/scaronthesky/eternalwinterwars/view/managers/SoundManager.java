package com.github.scaronthesky.eternalwinterwars.view.managers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;

import com.github.scaronthesky.eternalwinterwars.controller.IController;
/**
 * @author Manuel Seiche
 * @since 20.10.2014
 * 
 */
public class SoundManager extends AManager {
	private Map<MusicType, Music> gMusic;
	private Map<SoundType, Sound> gSounds;
	private MusicType gActualMusicType;

	public SoundManager(IController pController) {
		super(pController);
	}

	public enum MusicType {
		SPLASH, GAME
	}

	public enum SoundType {
		ATTACK
	}

	@Override
	public void load() {
		this.loadSounds();
		this.loadMusic();
	}

	@Override
	public void preLoad() {
		// The SplashScene's sounds and music have to be loaded here
	}

	private void loadMusic() {
		MusicFactory.setAssetBasePath("mfx/music/");
		this.gMusic = new HashMap<MusicType, Music>();
		this.loadMusic("game.mp3", true, MusicType.GAME);
	}

	private void loadSounds() {
		SoundFactory.setAssetBasePath("mfx/sounds/");
		this.gSounds = new HashMap<SoundType, Sound>();
		this.loadSound("attack.mp3", SoundType.ATTACK);
	}

	public Music loadMusic(String pMusicName, boolean pLoop,
			MusicType pMusicType) {
		Music lMusic = null;
		try {
			lMusic = MusicFactory.createMusicFromAsset(this.getController()
					.getMainActivity().getMusicManager(), this.getController()
					.getMainActivity(), pMusicName);
			lMusic.setLooping(pLoop);
			this.gMusic.put(pMusicType, lMusic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lMusic;
	}

	public Sound loadSound(String pSoundName, SoundType pSoundType) {
		Sound lSound = null;
		try {
			lSound = SoundFactory.createSoundFromAsset(this.getController()
					.getMainActivity().getSoundManager(), this.getController()
					.getMainActivity(), pSoundName);
			this.gSounds.put(pSoundType, lSound);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lSound;
	}

	public Music getActualMusic() {
		if (this.gMusic.containsKey(this.gActualMusicType)) {
			return this.gMusic.get(this.gActualMusicType);
		}
		return null;
	}

	public void setActualMusicType(MusicType pMusicType) {
		this.gActualMusicType = pMusicType;
	}

	public Map<MusicType, Music> getMusic() {
		return this.gMusic;
	}

	public Map<SoundType, Sound> getSounds() {
		return this.gSounds;
	}
}
