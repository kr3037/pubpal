package com.pubpal.app.pubpal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class nhie extends AppCompatActivity {

    ImageButton buttonback, buttoninfo, buttonnext;
    Dialog myDialogInfo;

    MediaPlayer mp;

    neverHave nhieInstanca = new neverHave();

    private class ClickListener implements View.OnClickListener {



        @Override
        public void onClick(View view) {
            buttonnext.setOnClickListener(null);
            final TextView tv1 = (TextView) findViewById(R.id.text);

            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(3000);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                public void onAnimationEnd(Animation anim) {
                    tv1.setText("Never Have I Ever...\n" + nhieInstanca.return_izziv());
                    buttonnext.setOnClickListener(new ClickListener());
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            tv1.startAnimation(animation);

            mp.start();

        }
    }

    private void hide() {
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

        mp = MediaPlayer.create(this, R.raw.airhorn

        );


        buttonnext = (ImageButton) findViewById(R.id.next);
        buttonnext.setOnClickListener(new ClickListener());

        final Context a = this;


        buttonback = (ImageButton) findViewById(R.id.buttonback2);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                finish();
            }
        });

        buttoninfo = (ImageButton) findViewById(R.id.info2);
        buttoninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogInfo = new Dialog(a, R.style.stildialog);
                myDialogInfo.setCanceledOnTouchOutside(false);

                myDialogInfo.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                myDialogInfo.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                myDialogInfo.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

                myDialogInfo.setContentView(R.layout.nhieinfo);
                myDialogInfo.setTitle("Info");

                myDialogInfo.findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialogInfo.dismiss();
                    }
                });

                myDialogInfo.show();


                myDialogInfo.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


            }
        });

    }

    private View mContentView;
    private View mControlsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_nhie);


        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        hide();

        //buttonnext.setSoundEffectsEnabled(false);

    }


    public class neverHave {


        public neverHave() {
            swap_izziv();
        }

        public String return_izziv() {

            index_izziv++;
            if (index_izziv == izziv.length) {
                swap_izziv();
                index_izziv = 0;
            }
            return izziv[index_izziv];
        }

        public void swap_izziv() {

            int first, second;
            String p;

            for (int i = 0; i < 1000; i++) {
                first = (int) (Math.random() * izziv.length);
                second = (int) (Math.random() * izziv.length);

                p = izziv[first];
                izziv[first] = izziv[second];
                izziv[second] = p;
            }

        }

        public int index_izziv = -1;
        public String izziv[] =
                {
                        "Sang karaoke",
                        "Fallen asleep in a cinema",
                        "Found money on the floor",
                        "Spent a night in a hospital",
                        "Broken up with someone because I wanted to be with someone else",
                        "Wore 2 deodorants at the same time",
                        "Witnessed a paranormal phenomena",
                        "Hit on someone while drunk",
                        "Worn underwear from someone of the opposite sex",
                        "Thrown up in a sink",
                        "Thrown up multiple times in one night",
                        "Drunk so much I didn\'t remember most of my night",
                        "Drunk water during \"Never Have I Ever\"",
                        "Been the only one who had to drink in this game",
                        "Gotten drunk while underage",
                        "Done drugs",
                        "Hired a sex worker",
                        "Been to a strip bar",
                        "Sneaked myself into a club",
                        "Skipped school/job because of a hangover",
                        "Drunk from a belly button",
                        "Thrown up in a car",
                        "Got thrown out of a bar",
                        "Was blackout drunk by 9pm",
                        "Uncapped a beer with my teeth",
                        "Tried to enter the wrong house when I was drunk",
                        "Started drinking in the morning",
                        "Consumed alcohol",
                        "Had fantasies about my mom",
                        "Played a drinking game",
                        "Been drunk for three whole days",
                        "Tried to make a person drunk",
                        "Been drunk in a cinema",
                        "Been drunk while driving",
                        "Been home alone drinking",
                        "Not payed my restaurant/bar bill",
                        "Exploited someone that was drunk",
                        "Done adrenaline sports",
                        "Slapped a person",
                        "Ran a marathon",
                        "Got lost while walking home drunk",
                        "Had a hangover",
                        "Been so drunk I got lost",
                        "Forgot my name because I was so drunk",
                        "Stolen someone's drink",
                        "Slept in the grass because I was drunk",
                        "Cheated on a test",
                        "Imagined how one of my teachers looked naked",
                        "Been in a food fight",
                        "Pretended I was sick for a week",
                        "Smoked in the school bathroom",
                        "Won an award",
                        "Gotten a free drink in a bar",
                        "Got stopped by the police while drunk",
                        "Drank vodka straight",
                        "Done body shots",
                        "Passed out next to the toilet",
                        "Had a Jagerbomb",
                        "Mixed beer with vodka",
                        "Fell down the stairs while drunk",
                        "Called my ex while drunk",
                        "Popped a condom",
                        "Kissed someone right after I threw up",
                        "Gone home from a party with someone of the opposite sex",
                        "Had a threesome",
                        "Had sex",
                        "Caught my partner cheating",
                        "Gotten three numbers in a single night out",
                        "Chased someone while drunk",
                        "Smoked weed",
                        "Done cocaine",
                        "Lost my wallet",
                        "Gone home after a moderate amount of just three beers",
                        "Arranged with friends to go out for a few beers and then drank way too much",
                        "Ordered two pizzas for myself",
                        "Gone to a smoothie bar",
                        "Tried exotic food",
                        "Driven a truck",
                        "Driven a motorcycle",
                        "Washed my own laundry",
                        "Kissed someone\'s feet",
                        "Picked my nose and ate it",
                        "Used a fake I.D.",
                        "Snuck into a club",
                        "Bragged about something I have not done",
                        "Doubted in my heterosexuality",
                        "Bet on something",
                        "Escaped from class",
                        "Lied in this game",
                        "Stuck gum under a desk",
                        "Been unfaithful",
                        "Kissed someone of the same sex",
                        "Got drunk playing these games",
                        "Kissed someone without knowing him/her",
                        "Been with the former love of my best friend",
                        "Fallen asleep on the bus and missed my station",
                        "Been robbed",
                        "Lied to my parents about where I\'m going",
                        "Called in sick to work because I was hungover",
                        "Made out with a stranger",
                        "Made out while listening to Nickleback",
                        "Re-gifted a gift that was given to me",
                        "Sniffed my underwear to see if it\'s clean or dirty",
                        "Dined and dashed",
                        "Said \"I love you\" just to have sex with someone",
                        "Been slapped across the face",
                        "Gone to work/school in a mind-altered state from drugs or alcohol",
                        "Used a common household item as a sex toy and put it back where it belongs",
                        "Blown a line of cocaine off of someone\'s body.",
                        "Deleted a post on social media because I had a low percentage of likes",
                        "Woken up in a strange place without remembering how I got there",
                        "Taken nude pictures of a friend",
                        "Puked on someone while hooking up with them",
                        "Slurred a racist remark toward someone",
                        "Grabbed a random woman\'s ass without permission",
                        "Grabbed a man\'s penis without permission",
                        "Punched someone",
                        "Participated in a protest",
                        "Licked someone\'s butt",
                        "Cried while drunk",
                        "Shattered my phone screen",
                        "Starved myself for the sake of losing weight",
                        "Looked at teachers ass"
                };
    }
}
