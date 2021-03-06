package com.github.scaronthesky.eternalwinterwars.view;

/**
 * @author Manuel Seiche
 * @since 21.10.2014
 * 
 */
public abstract class Constants {
	public static final String ANIMATION_KEY_MOVE_LEFT = "move_left";
	public static final String ANIMATION_KEY_MOVE_RIGHT = "move_right";
	public static final String ANIMATION_KEY_MOVE_UP = "move_up";
	public static final String ANIMATION_KEY_MOVE_DOWN = "move_down";
	public static final String ANIMATION_KEY_ATTACK_LEFT = "attack_left";
	public static final String ANIMATION_KEY_ATTACK_RIGHT = "attack_right";
	public static final String ANIMATION_KEY_ATTACK_UP = "attack_up";
	public static final String ANIMATION_KEY_ATTACK_DOWN = "attack_down";
	public static final String ANIMATION_KEY_IDLE = "idle";
	public static final String ANIMATION_KEY_DIE = "die";

	public static final float UNIT_MOVE_DURATION_PER_CELL = 0.5f;
	public static final float UNIT_CREATE_DURATION = 0.5f;

	public static final float PARTICLE_CALC_PIXELS_PER_SNOWFLAKE = 4608f;

	public static final float INCOME_MOVE_TO_DEST_DURATION = 2f;
	public static final float DAMAGE_MOVE_TO_DEST_DURATION = 1.5f;

	public static final float CAMERA_CONTROL_MOVE_MULTIPLICATOR = 50f;
	public static final float CAMERA_CONTROL_BASE_APPEAR_DURATION = 0.5f;
	public static final float RANGED_ATTACK_FLY_DURATION_PER_PIXEL = 0.003f;

	public static final int GAME_BASE_ENTITY_UNIT_KNIGHT_HEALTH = 100;
	public static final int GAME_BASE_ENTITY_UNIT_MARKSMAN_HEALTH = 70;
	public static final int GAME_BASE_ENTITY_UNIT_ARTILLERY_HEALTH = 150;
	public static final int GAME_BASE_ENTITY_UNIT_CAVALLERY_HEALTH = 120;
	public static final int GAME_BASE_ENTITY_BUILDING_CASTLE_HEALTH = 600;

	public static final float GAME_BASE_ENTITY_HEALTHBAR_HEIGHT_PERC = 0.1f;

}
