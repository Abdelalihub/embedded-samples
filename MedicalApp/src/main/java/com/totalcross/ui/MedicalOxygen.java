package com.totalcross.ui;

import com.totalcross.MedicalApp;
import com.totalcross.knowcode.parse.CustomInitUI;
import com.totalcross.knowcode.parse.XmlContainerFactory;
import com.totalcross.knowcode.parse.XmlContainerLayout;
import com.totalcross.util.Fonts;
import com.totalcross.util.Positioning;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.MainWindow;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.image.ImageException;

public class MedicalOxygen extends Container {

    private Container homeContainer;

    MedicalOxygen(Container homeContainer) {
        this.homeContainer = homeContainer;
    }

    @Override
    public void initUI() {
        Container container = XmlContainerFactory.create("xml_files/medical_oxygen.xml");
        XmlContainerLayout containerLayout = (XmlContainerLayout) container;
        containerLayout.setCustomInitUI(new CustomInitUI() {

            @Override
            public void postInitUI(XmlContainerLayout container) {
                // Fonts
                container.getControlByID("@+id/titleLabel").setFont(Fonts.BARLOW_CONDENSED_50);
                container.getControlByID("@+id/oxygenLabel").setFont(Fonts.BARLOW_CONDENSED_150);
                container.getControlByID("@+id/percentLabel").setFont(Fonts.BARLOW_CONDENSED_50);
                container.getControlByID("@+id/oxygenMinMaxLabel").setFont(Fonts.BARLOW_CONDENSED_24);
                container.getControlByID("@+id/lungsLabel").setFont(Fonts.BARLOW_CONDENSED_17);
                container.getControlByID("@+id/flowLabel").setFont(Fonts.BARLOW_CONDENSED_25);
                container.getControlByID("@+id/minSpO2Label").setFont(Fonts.BARLOW_CONDENSED_25);
                container.getControlByID("@+id/controlsLabel").setFont(Fonts.BARLOW_CONDENSED_25);
                container.getControlByID("@+id/notifyLabel").setFont(Fonts.BARLOW_CONDENSED_17);
                container.getControlByID("@+id/setAlarmLabel").setFont(Fonts.BARLOW_CONDENSED_17);
                container.getControlByID("@+id/saveButton").setFont(Fonts.BARLOW_CONDENSED_20);
                container.getControlByID("@+id/cancelButton").setFont(Fonts.BARLOW_CONDENSED_20);

                DataGraph dataGraph = new DataGraph(50, 99);
                dataGraph.addValues(new int[] { 50, 75, 36, 47, 59, 63, 54, 52, 45 });
                dataGraph.setColor(0x00A8DB);
                container.add(dataGraph, Positioning.getWidthDP(45), Positioning.getHeightDP(430),
                        Positioning.getWidthDP(359), Positioning.getHeightDP(162));
                CircleProgress flowCircleProgress = new CircleProgress(20, MedicalHome.oxygenMaxValue,
                        MedicalHome.oxygenValue, MedicalHome.oxygenValue + "");
                flowCircleProgress.setFont(Fonts.BARLOW_CONDENSED_54);
                flowCircleProgress.setFilledColor(0x00A8DB);
                flowCircleProgress.setUnfilledColor(0x1A5364);
                flowCircleProgress.setBackColor(0x003F52);
                flowCircleProgress.setTextColor(0x00A8DB);
                container.add(flowCircleProgress, Positioning.getWidthDP(480), Positioning.getHeightDP(80),
                        Positioning.getHeightDP(200), Positioning.getHeightDP(200));
                CircleProgress spo2CircleProgress = new CircleProgress(20, MedicalHome.oxygenMaxValue,
                        MedicalHome.oxygenValue, MedicalHome.oxygenValue + "%");
                spo2CircleProgress.setFont(Fonts.BARLOW_CONDENSED_54);
                spo2CircleProgress.setFilledColor(0x00A8DB);
                spo2CircleProgress.setUnfilledColor(0x1A5364);
                spo2CircleProgress.setBackColor(0x003F52);
                spo2CircleProgress.setTextColor(0x00A8DB);
                container.add(spo2CircleProgress, Positioning.getWidthDP(480), Positioning.getHeightDP(389),
                        Positioning.getHeightDP(200), Positioning.getHeightDP(200));
                SemiCircleProgress semiCircleProgress = new SemiCircleProgress(20, 99, 84, SemiCircleProgress.LEFT);
                semiCircleProgress.setFilledColor(0x00A8DB);
                semiCircleProgress.setUnfilledColor(0x1A5364);
                semiCircleProgress.setBackColor(0x003F52);
                container.add(semiCircleProgress, Positioning.getWidthDP(241), Positioning.getHeightDP(46),
                        Positioning.getWidthDP(162), Positioning.getHeightDP(328));
                Control img = container.getControlByID("@+id/lungsImage");
                Control imgLabel = container.getControlByID("@+id/lungsLabel");
                container.add(img);
                container.add(imgLabel);
                ControlPanel controlPanel = new ControlPanel(99, 42);
                controlPanel.setFont(Fonts.BARLOW_CONDENSED_25);
                controlPanel.setFilledColor(0x00A8DB);
                controlPanel.setUnfilledColor(0x1A5364);
                controlPanel.setTextColor(0xFFFFFF);
                container.add(controlPanel, Positioning.getWidthDP(754), Positioning.getHeightDP(115),
                        Positioning.getWidthDP(120), Positioning.getHeightDP(262));
                Button saveButton = (Button) container.getControlByID("@+id/saveButton");
                saveButton.setBorder(Button.BORDER_ROUND);
                saveButton.setFont(Fonts.BARLOW_CONDENSED_20);
                saveButton.roundBorderFactor = 2;
                saveButton.setBackForeColors(0xffffff, 0x00A8DB);
                container.add(saveButton);
                Button cancelButton = (Button) container.getControlByID("@+id/cancelButton");
                cancelButton.transparentBackground = true;
                cancelButton.setBorder(Button.BORDER_OUTLINED);
                cancelButton.setFont(Fonts.BARLOW_CONDENSED_20);
                cancelButton.roundBorderFactor = 2;
                cancelButton.borderColor = 0x00A8DB;
                cancelButton.setBackColor(0x00A8DB);
                container.add(cancelButton);
                try {
                    Button backButton = (Button) container.getControlByID("@+id/imageButton");
                    backButton.transparentBackground = false;
                    backButton.setBackColor(0xFFFFFF);
                    backButton.setImage(backButton.getImage().getScaledInstance(Positioning.getWidthDP(21),
                            Positioning.getHeightDP(34)));
                    backButton.addPressListener(new PressListener() {

                        @Override
                        public void controlPressed(ControlEvent arg0) {
                            MainWindow.getMainWindow().swap(homeContainer);
                        }
                    });
                } catch (ImageException e) {
                    e.printStackTrace();
                }
            }

        });
        MainWindow.getMainWindow().swap(container);
    }
}