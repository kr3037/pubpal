package com.pubpal.app.pubpal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class bottle extends AppCompatActivity {

    public static final Random rand = new Random();
    private View main;
    private ImageView bottleimg;
    MediaPlayer mp;
    private int lastAngle = -1;




    Dialog myDialog, myDialogTruth;


    TextView truthText;



    spin_bottle sbInstanca = new spin_bottle();

    private void hide(){
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mControlsView.setVisibility(View.GONE);


        mp = MediaPlayer.create(this, R.raw.spin1t);





        buttonback = (ImageButton) findViewById(R.id.buttonback);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                finish();
            }
        });

        bottleimg = (ImageView) findViewById(R.id.bottleimage);

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinTheBottle();
            }
        });
    }




    private View mContentView;
    private View mControlsView;


    ImageButton buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_bottle);


        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        hide();

    }

    private void spinTheBottle() {

        mContentView.setOnClickListener(null);

        int angle = rand.nextInt(360);

        float pivotX = bottleimg.getWidth() / 2;
        float pivotY = bottleimg.getHeight() / 2;

        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, (1800 + angle), pivotX, pivotY);

        animRotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mp.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        truthordare();
                    }
                }, 1000);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        lastAngle = angle;
        animRotate.setDuration(4100);
        animRotate.setFillAfter(true);

        bottleimg.startAnimation(animRotate);


    }

    private void truthordare(){
        myDialog = new Dialog(this, R.style.stildialog);
        myDialog.setCanceledOnTouchOutside(false);

        final Context a = this;

        myDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        myDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        myDialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        myDialog.setContentView(R.layout.truthordare);
        myDialog.setTitle("Truth or dare?");

        myDialog.findViewById(R.id.truth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                myDialogTruth = new Dialog(a, R.style.stildialog);
                myDialogTruth.setCanceledOnTouchOutside(false);

                myDialogTruth.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                myDialogTruth.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                myDialogTruth.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

                myDialogTruth.setContentView(R.layout.truthdialog);
                myDialogTruth.setTitle("Truth");



                myDialogTruth.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialogTruth.dismiss();

                        mContentView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                spinTheBottle();
                            }
                        });

                    }
                });







                //setContentView(R.layout.truthdialog);
                TextView tv1 = (TextView)myDialogTruth.findViewById(R.id.textTruth);

                tv1.setText(sbInstanca.return_challenge(false));

                myDialogTruth.show();

                //System.out.println(tv1.getText());

                myDialogTruth.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            }
        });

        myDialog.findViewById(R.id.dare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

                myDialogTruth = new Dialog(a, R.style.stildialog);
                myDialogTruth.setCanceledOnTouchOutside(false);

                myDialogTruth.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                myDialogTruth.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                myDialogTruth.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

                myDialogTruth.setContentView(R.layout.truthdialog);
                myDialogTruth.setTitle("Dare");



                myDialogTruth.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialogTruth.dismiss();

                        mContentView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                spinTheBottle();
                            }
                        });

                    }
                });







                //setContentView(R.layout.truthdialog);
                TextView tv1 = (TextView)myDialogTruth.findViewById(R.id.textTruth);

                tv1.setText(sbInstanca.return_challenge(true));

                myDialogTruth.show();

                //System.out.println(tv1.getText());

                myDialogTruth.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            }
        });


        myDialog.show();


        myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }















    public class spin_bottle{


        public spin_bottle(){
            swap_spin_bottle();
        }

        public String return_challenge(boolean izziv){

            if(izziv){
                index_dare++;
                if(index_dare == dare.length){
                    swap_dares();
                    index_dare = 0;
                }
                return dare[index_dare];
            }
            index_truth++;
            if(index_truth == truth.length){
                swap_truths();
                index_truth = 0;
            }
            return truth[index_truth];
        }
        public void swap_spin_bottle(){

            int first, second;
            String p;

            for(int i=0; i<1000; i++){
                first = (int)(Math.random() * (dare.length ));
                second = (int)(Math.random() * (dare.length ));

                p = dare[first];
                dare[first] = dare[second];
                dare[second] = p;
            }
            for(int i=0; i<1000; i++){
                first = (int)(Math.random() * (truth.length ));
                second = (int)(Math.random() * (truth.length ));

                p = truth[first];
                truth[first] = truth[second];
                truth[second] = p;
            }
        }
        public void swap_dares(){

            int first, second;
            String p;

            for(int i=0; i<1000; i++){
                first = (int)(Math.random() * (dare.length ));
                second = (int)(Math.random() * (dare.length ));

                p = dare[first];
                dare[first] = dare[second];
                dare[second] = p;
            }
        }
        public void swap_truths(){

            int first, second;
            String p;

            for(int i=0; i<1000; i++){
                first = (int)(Math.random() * (truth.length ));
                second = (int)(Math.random() * (truth.length ));

                p = truth[first];
                truth[first] = truth[second];
                truth[second] = p;
            }
        }

        public int index_dare = -1;
        public String dare[] =
                {
                        "Open facebook and like the first thing that pops up",
                        "Order a drink that you dislike the most",
                        "You owe the person next to you a drink",
                        "Ask the first person of the opposite sex for their number",
                        "Tell your worst joke",
                        "Mix beer with vodka",
                        "Show everything that\'s in your pockets",
                        "Declare what\'s your true love",
                        "Tell the person playing with you that you love them",
                        "Challenge the other player to arm wrestling",
                        "Everytime someone performs a dare yell LOSER",
                        "Go 10 minutes without drinking",
                        "Go to the toilet and wash your face",
                        "Call McDonalds and ask if they sell Whoppers",
                        "Let someone check through your phone for 1 minute",
                        "Act like a waiter and take someone\'s order",
                        "Call Burger King and ask if they sell Big Mac",
                        "Chug your drink",
                        "Kiss or brofist the person opposite of you. Depenending on the gender",
                        "Talk in a fake accent for the rest of the game",
                        "Drink a glass of water",
                        "Stare at the ground ",
                        "Text the 3rd person in your recent contacts list",
                        "Let one of the players text someone from your phone",
                        "Give someone else your drink",
                        "Spend the next round sitting on someone\'s lap",
                        "Dance with the player in front of you",
                        "Send a text to your ex: \"Can i come over?\"",
                        "Take an instagram photo",
                        "Stare at a random person with anger",
                        "Choose a player to have a thumb war with. Loser has to drink!",
                        "Show everyone the last 2 text messages you sent",
                        "Do a celebrity impression. If the group can\'t guess, you drink",
                        "Tell everyone who you would fuck right now",
                        "Do a sexy face photo and make it your profile picture for the night.",
                        "Name a color. Everyone wearing that color has to drink",
                        "For however long you can hold an ice cube in your mouth the group had to drink",
                        "The next 3 choices you make have to be truths",
                        "Switch seats with the drunkest player",
                        "Send a friend request to a random person on facebook",
                        "Go to the bathroom and wash your hands",
                        "Order the cheapest shot and down it as soon as you get it",
                        "Act like Hitler for the next 10 rounds",
                        "Update your Facebook status asking if anyone knows who meatrocket69 on Chatroullete is?",
                        "The person with the most expensive clothes must buy you a drink",
                        "Pick someone to write a sexy text to your crush",
                        "Drop a shot into a glass of beer and chug!",
                        "Drink your beer super slowly",
                        "Ask the waitress who the sexiest person at the table is",
                        "How many songs Beatles songs can you name? For every one, the group has to drink",
                        "Leave the bar and go back in",
                        "You can give away the next dare you get to any person at this table!",
                        "High five everybody at the table",
                        "Google \"bondage machine\"",
                        "Give nicknames to everyone at the table for the night",
                        "On Facebook, like one of the photos of your crush",
                        "Stand up! You must now stand until your next turn",
                        "Blow a kiss to a random person",
                        "Send a text message saying \"I love you\" to three people",
                        "Burp",
                        "Show your friends your ID photo",
                        "Close your eyes and send a blind text to a random person",
                        "Yell out the first word that comes to your mind right now",
                        "Take a shot",
                        "Take a shot off of the person to your right",
                        "Give the person to your right a hickie",
                        "Try to chug a bottle of beer in less than 20 seconds.",
                        "Make an unflattering picture of yourself your Facebook profile picture for at least a day",
                        "Pick your nose in front of everyone",
                        "Let someone in the room write whatever they want from your Facebook account",
                        "Lick the side of someone\'s face.",
                        "Perform a rap for everyone in the room.",
                        "Send a love letter to someone on Facebook",
                        "Post a video of you singing and share it on your social media account",
                        "Go outside and try to hug the next person that walks by",
                        "Give a kiss to each player in the room. A peck on the lips is okay",
                        "Go outside and pretend that you are an airplane for 2 minutes",
                        "Lick ear of a person on your left",
                        "Touch your friends nose with your tongue only",
                        "Bark like a dog for 30 seconds",
                        "Show your whole browsing history to the players in the room",
                        "Prank call someone and tell them that you are horny",
                        "Do a belly dance",
                        "Give someone your phone and let them send one text to anyone in your contacts",
                        "Sniff the armpits of everyone in the room",
                        "Let the group look through your phone for 2 minutes",
                        "Stick your arm into the trash can past your elbow",
                        "Get slapped on the face by the person of your choosing",
                        "Choose someone from the group to give you a spanking",
                        "Go to the bathroom, take off your underwear and put it on your head. Wear it on your head for the rest of the game",
                        "Act like whatever animal someone yells out for the next 1 minute",
                        "Call the 7th contact in your phone and sing them 30 seconds of a song that the group chooses",
                        "Ask a random girl/boy for a kiss, if she/he ok, go for it"
                };

        public int index_truth = -1;
        public String truth[] =
                {
                        "How much money did you make last year?",
                        "What question would you like to ask another player?",
                        "How many people did you sleep with?",
                        "If your significant other said it was ok, would you cheat on them?",
                        "What were your first impressions of the player opposite of you?",
                        "If you had to kiss anyone in this room who would it be?",
                        "What is your biggest regret?",
                        "What is your biggest fear?",
                        "What do you want to be when you grow up?",
                        "Who was your first kiss?",
                        "Whats your biggest turn on?",
                        "Whats your biggest turn off?",
                        "Do you have a nickname?",
                        "Did you ever steal something?",
                        "Who was your first love?",
                        "What is your best drunk adventure?",
                        "If you could be alone on an island with only one person who would it be?",
                        "What would your superpower be?",
                        "What is the weirdest thing you have ever eaten?",
                        "Have you ever cheated on your boyfriend/girlfriend?",
                        "Spill the last secret someone told you",
                        "Have you ever been caught by the police?",
                        "What is the weirdest porn you ever saw?",
                        "Have you ever pulled an all nighter?",
                        "What do you act like drunk?",
                        "Have you ever used a sex toy?",
                        "When was the last time you had sex?",
                        "What is the worst place you publicly urinated at",
                        "How do you handle your hangovers?",
                        "When was the first time you were blackout drunk?",
                        "Whats the weirdest place you ever had sex?",
                        "Have you ever been in a threesome?",
                        "Have you ever stolen a drink at a party?",
                        "Point at the person you think looks the best in the room",
                        "Would you have sex with the person behind you?",
                        "How many books did you read?",
                        "Do you have an online dating profile?",
                        "Have you ever stripped?",
                        "If you could make someone in this room your slave who would you pick and what would you make them do?",
                        "What is the best gift you have ever given someone? ",
                        "What color is your underwear?",
                        "Have you ever been arrested?",
                        "Have you ever shoplifted?",
                        "What parts of your body do you shave?",
                        "Who in this room has the best looking mom?",
                        "Which two people in this room would make the worst couple?",
                        "What disney character do you think is the hottest?",
                        "Have you ever gotten fired from a job? Why?",
                        "What is the most embarrassing thing you've done while being drunk?",
                        "Who in this room annoys you the most?",
                        "Would you vote for Trump or Hillary?",
                        "What would you do if you became invisible for one day?",
                        "If you were food. What food would you be?",
                        "If you had to have sex with an animal what animal would it be?",
                        "Do you think you are perfect marriage material?",
                        "Who do you like the most in this room?",
                        "Do you pick your nose?",
                        "Have you ever fallen asleep in class/on a job?",
                        "Do you sing in the shower?",
                        "Have you ever walked into a wall?",
                        "Have you ever lied about your age?",
                        "Would you rather eat dog food or cat food?",
                        "Who is your celebrity crush?",
                        "Would you rather not shower for a month or eat the same meal everyday for a month?",
                        "If you switched genders for a day what would you do?",
                        "Do you ever talk to yourself in the mirror?",
                        "What makes you happy?",
                        "Whats the best thing about the person next to you?",
                        "Do you lick your plate?",
                        "What does your web history look like?",
                        "If a fish granted you 3 wishes, what would they be?",
                        "Have you ever cheated on a test?",
                        "How many selfies do you take a month?",
                        "Were you ever in a car accident?",
                        "What is something you\'ve done to try to be cooler?",
                        "If you could use one swear word for the rest of your life, which would you choose?",
                        "Have you ever peed yourself?",
                        "What sport did you always want to try?",
                        "When was the last time you had a confession in church?",
                        "If you could be any type of animal what would you be?",
                        "Do you like to flirt when at a party?",
                        "What is the most you have ever drank?",
                        "What is your favourite drink?",
                        "What is your worst habit?",
                        "What is the best part of your body?",
                        "Do you prefer shots or cocktails?",
                        "Do you stay out late while partying?",
                        "What is the best party you ever went to?",
                        "Do you know any dance moves? If so, which?",
                        "What are you most self-conscious about?",
                        "What is the most childish thing you still do?",
                        "What is the most expensive thing you have stolen?",
                        "Who here would you most like to make out with?",
                        "Have you ever cheated or been cheated on?",
                        "What is your deepest darkest fear?",
                        "Where is the strangest place you have peed?",
                        "What is the most embarrassing thing in your room?",
                        "What is the stupidest thing you have ever done?",
                        "Who is the sexiest person here?",
                        "What is the grossest thing that has come out of your body?",
                        "What is something that you have never told anyone?",
                        "What is the most disgusting habit you have?",
                        "What is the grossest thing you have had in your mouth?",
                        "What is the biggest lie you have ever told?",
                        "What is your biggest regret?",
                        "What do you really hope your parents never find out about?",
                        "Tell us your most embarrassing vomit story.",
                        "Have you ever made out with someone here?",
                        "What have you done that people here would judge you most for doing?",
                        "Who is your crush?",
                        "If you had to date someone in this room, who would it be?",
                        "Who is the last person that you stalked on social media?",
                        "What habit can\'t you seem to quit?",
                        "What don?t you like about your boyfriend or girlfriend?",
                        "Have you ever hooked up with the same sex?",
                        "Would you stop talking to all of your friends for a million dollars?",
                        "If you and one person in this room could be the last people on Earth left alive, who would that person be?",
                        "Who in this room do you think would be the best date?",
                        "When is the last time you cried?",
                        "What is the most embarrassing thing that you have ever done in front of a crush?",
                        "If you could only save one person in this room from a fire, who would it be?",
                        "Who is the most annoying person in the room?",
                        "Who is the most attractive person in the room?",
                        "Have you ever wanted to kill someone?",
                        "If you watch x-rated movies, what type is your favorite?",
                        "Do you have a favorite \"adult\" site?",
                        "What is an unpopular opinion that you have?",


                };
    }
}
