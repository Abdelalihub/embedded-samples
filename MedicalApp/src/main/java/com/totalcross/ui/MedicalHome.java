package com.totalcross.ui;

import com.totalcross.knowcode.parse.CustomInitUI;
import com.totalcross.knowcode.parse.XmlContainerFactory;
import com.totalcross.knowcode.parse.XmlContainerLayout;
import com.totalcross.util.Fonts;
import com.totalcross.util.Positioning;

import totalcross.sys.Settings;
import totalcross.sys.Time;
import totalcross.ui.Container;
import totalcross.ui.Divider;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.util.Date;

public class MedicalHome extends Container {

    public static int pulseValue = 110;
    public static int pulseMinValue = 50;
    public static int pulseMaxValue = 130;
    public static int oxygenValue;
    public static int oxygenMaxValue;
    private static Date date = new Date();
    private static Time time = new Time();
    private Container container;
    private static final String[] DAYS_OF_WEEK = { "SUN", "MON", "THU", "WED", "TUE", "FRI", "SAT" };

    @Override
    public void initUI() {
        container = XmlContainerFactory.create("xml_files/medical_home.xml");
        XmlContainerLayout containerLayout = (XmlContainerLayout) container;
        containerLayout.setCustomInitUI(new CustomInitUI() {

            @Override
            public void postInitUI(XmlContainerLayout container) {
                // Fonts
                container.getControlByID("@+id/oxygenTopLabel").setFont(Fonts.BARLOW_CONDENSED_50);
                container.getControlByID("@+id/timeLabel").setFont(Fonts.BARLOW_CONDENSED_20);
                container.getControlByID("@+id/oxygenLabel").setFont(Fonts.BARLOW_CONDENSED_150);
                container.getControlByID("@+id/percentLabel").setFont(Fonts.BARLOW_CONDENSED_50);
                container.getControlByID("@+id/oxygenMinMaxLabel").setFont(Fonts.BARLOW_CONDENSED_24);
                container.getControlByID("@+id/lungsLabel").setFont(Fonts.BARLOW_CONDENSED_24);
                container.getControlByID("@+id/pulseTopLabel").setFont(Fonts.BARLOW_CONDENSED_50);
                container.getControlByID("@+id/pulseLabel").setFont(Fonts.BARLOW_CONDENSED_150);
                container.getControlByID("@+id/pulseMinMaxLabel").setFont(Fonts.BARLOW_CONDENSED_24);
                container.getControlByID("@+id/heartLabel").setFont(Fonts.BARLOW_CONDENSED_24);

                Divider oxygenDivider = new Divider();
                oxygenDivider.setBackColor(0x00A8DB);
                Divider pulseDivider = new Divider();
                pulseDivider.setBackColor(0xFF0000);
                container.add(oxygenDivider, Positioning.getWidthDP(89), Positioning.getHeightDP(388),
                        Positioning.getWidthDP(55), Positioning.getHeightDP(1));
                container.add(pulseDivider, Positioning.getWidthDP(803), Positioning.getHeightDP(388),
                        Positioning.getWidthDP(55), Positioning.getHeightDP(1));
                Label dateLabel = (Label) container.getControlByID("@+id/timeLabel");
                dateLabel.setText(time.toString().substring(0, 5) + " - " + DAYS_OF_WEEK[date.getDayOfWeek()] + ", "
                        + date.toString().substring(0, date.toString().length() - 4)
                        + date.toString().substring(date.toString().length() - 2, date.toString().length()));
                add(dateLabel);
                SemiCircleProgress oxygenSemiCircleProgress = new SemiCircleProgress(20, 99, 84,
                        SemiCircleProgress.LEFT);
                oxygenSemiCircleProgress.setFilledColor(0x00A8DB);
                oxygenSemiCircleProgress.setUnfilledColor(0x1A5364);
                oxygenSemiCircleProgress.setUseGradient(true);
                oxygenSemiCircleProgress.setGradientBack(0x22202A);
                oxygenSemiCircleProgress.setGradientFore(0x142D3B);
                add(oxygenSemiCircleProgress, Positioning.getWidthDP(255), Positioning.getHeightDP(96),
                        Positioning.getWidthDP(211), Positioning.getHeightDP(448));
                SemiCircleProgress pulseSemiCircleProgress = new SemiCircleProgress(20, 99, 84,
                        SemiCircleProgress.RIGHT);
                pulseSemiCircleProgress.setFilledColor(0xFF0000);
                pulseSemiCircleProgress.setUnfilledColor(0x55474D);
                pulseSemiCircleProgress.setUseGradient(true);
                pulseSemiCircleProgress.setGradientBack(0x241F28);
                pulseSemiCircleProgress.setGradientFore(0x321218);
                add(pulseSemiCircleProgress, Positioning.getWidthDP(495), Positioning.getHeightDP(96),
                        Positioning.getWidthDP(211), Positioning.getHeightDP(448));
                add(container.getControlByID("@+id/lungsImage"));
                add(container.getControlByID("@+id/lungsLabel"));
                add(container.getControlByID("@+id/heartImage"));
                add(container.getControlByID("@+id/heartLabel"));
                oxygenSemiCircleProgress.addPenListener(new PenListener() {

                    @Override
                    public void penDown(PenEvent pe) {
                        if (!Settings.fingerTouch || !hadParentScrolled()) {
                            MainWindow.getMainWindow().swap(new MedicalOxygen(MedicalHome.this.container));

                        }
                    }

                    @Override
                    public void penDrag(DragEvent arg0) {
                    }

                    @Override
                    public void penDragEnd(DragEvent arg0) {
                    }

                    @Override
                    public void penDragStart(DragEvent arg0) {
                    }

                    @Override
                    public void penUp(PenEvent pe) {
                        if (!Settings.fingerTouch || !hadParentScrolled()) {
                            MainWindow.getMainWindow().swap(new MedicalOxygen(MedicalHome.this.container));
                        }
                    }

                });
                pulseSemiCircleProgress.addPenListener(new PenListener() {

                    @Override
                    public void penDown(PenEvent pe) {
                        if (!Settings.fingerTouch || !hadParentScrolled()) {
                            MainWindow.getMainWindow().swap(new MedicalPulse(MedicalHome.this.container));
                        }
                    }

                    @Override
                    public void penDrag(DragEvent arg0) {
                    }

                    @Override
                    public void penDragEnd(DragEvent arg0) {
                    }

                    @Override
                    public void penDragStart(DragEvent arg0) {
                    }

                    @Override
                    public void penUp(PenEvent pe) {
                        if (!Settings.fingerTouch || !hadParentScrolled()) {
                            MainWindow.getMainWindow().swap(new MedicalPulse(MedicalHome.this.container));
                        }
                    }

                });
            }

        });
        add(container, LEFT, TOP, FILL, FILL);
    }

}
