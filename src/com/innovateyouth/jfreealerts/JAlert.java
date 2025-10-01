/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.innovateyouth.jfreealerts;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Sachira Jayawardana
 */
public class JAlert extends javax.swing.JPanel {

    private final JPanel root;
    private int x = 400, y = 0, width = 390, height = 80;
    private int gap;
    private boolean isRemoved;
    private Runnable callBack;
    private JAlertType alertType;
    private String title;
    private String message;
    private String soundClipPath;

    int r, g, b;
    private static String assetSourcePath = "/com/innovateyouth/jfreealerts/asset/";

    public JAlert(JPanel root) {
        initComponents();
        this.root = root;
        applyProperties();
        addSelfToRoot();
    }

    public JAlert(JPanel root, JAlertType alertType, String message) {
        this(root);
        setAlertType(alertType);
        setMessage(message);
    }

    /**
     * Callback fired soon after the alert is dismissed
     *
     * @param callBack callback action of type Runnable
     * @return the updated JAlert instance
     */
    public JAlert setCallBack(Runnable callBack) {
        this.callBack = callBack;
        return this;
    }

    public JAlert setMessage(String message) {
        this.message = message;
        this.messageLabel.setText(message);
        return this;
    }

    public JAlert setTitle(String title) {
        this.title = title;
        this.titleLabel.setText(title);
        return this;
    }

    public JAlert setIcon(ImageIcon icon) {
        this.alertIconLabel.setIcon(icon);
        return this;
    }

    public JAlert setAlertType(JAlertType type) {
        this.alertType = type;
        String titleText;
        String imageIconPath = assetSourcePath;
        Color accesntColor;
        String borderColor;
        switch (type) {
            case ERROR -> {
                titleText = "Error Message";
                imageIconPath += "error_icon.png";
                accesntColor = new Color(159, 76, 76);
                borderColor = "#9F4C4C";
                r = 255;
                g = 245;
                b = 245;
                soundClipPath = "Windows Error.wav";
            }
            case INFO -> {
                titleText = "Message";
                imageIconPath += "info_icon.png";
                accesntColor = new Color(91, 117, 160);
                borderColor = "#5B75A0";
                r = 245;
                g = 245;
                b = 255;
                soundClipPath = "Windows Notify Email.wav";
            }
            case SUCCESS -> {
                titleText = "Success";
                imageIconPath += "success_icon.png";
                accesntColor = new Color(105, 159, 76);
                borderColor = "#009966";
                r = 245;
                g = 255;
                b = 245;
                soundClipPath = "Windows Notify.wav";
            }
            case WARNING -> {
                titleText = "Warning";
                imageIconPath += "warn_icon.png";
                accesntColor = new Color(191, 135, 62);
                borderColor = "#BF873E";
                r = 255;
                g = 255;
                b = 245;
                soundClipPath = "Windows Exclamation.wav";
            }
            default -> {
                titleText = "Message title";
                imageIconPath += "check_32.png";
                accesntColor = Color.GREEN;
                borderColor = "#5B75A0";
                r = 245;
                g = 255;
                b = 245;
            }
        }
        setTitle(title != null ? title : titleText);
        setIcon(new ImageIcon(getClass().getResource(imageIconPath)));
        this.putClientProperty(FlatClientProperties.STYLE, "border:0,0,2,0,%s33,1,10"
                .formatted(borderColor));
        accentBar.setBackground(accesntColor);
        return this;
    }

    public JAlertType getAlertType() {
        return alertType;
    }

    // change 1
    @Override
    public void show() {
        checkEDT("show");
        // Initial setup on EDT
        SwingUtilities.invokeLater(() -> {
            makeNotificationPanelVisible();
            startSlideInAnimation();
        });
        playSound();
    }

    private void startSlideInAnimation() {
        final double[] d = { 1.0 };
        final double ol = this.getLocation().getX();
        final double[] j = { ol };

        Timer slideTimer = new Timer(1, null);
        slideTimer.addActionListener(e -> {
            if (j[0] > 0) {
                updatePosition(j[0]); // Now runs on EDT

                // Handle acceleration motion
                if (ol / j[0] >= 2) {
                    d[0] -= 0.5;
                } else {
                    d[0] += 0.5;
                }
                if (d[0] <= 0) {
                    d[0] = 1;
                }

                j[0] -= 0.1 * d[0];
            } else {
                slideTimer.stop();
                // Continue to next animation
                animateProgressBar();
            }
        });
        slideTimer.start();
    }

    private void makeNotificationPanelVisible() {
        toggleRootVisibility(true);

        // Use Swing Timer for fade-in animation on EDT
        Timer fadeTimer = new Timer(1, null);
        final int[] alpha = { 0 };

        fadeTimer.addActionListener(e -> {
            if (alpha[0] < 240) {
                this.setBackground(new Color(r, g, b, alpha[0]));
                root.revalidate();
                root.repaint();
                alpha[0]++;
            } else {
                fadeTimer.stop();
            }
        });
        fadeTimer.start();
    }

    // change 2
    public void updatePosition(double j) {
        checkEDT("updatePosition");
        // Check if we're on EDT
        if (SwingUtilities.isEventDispatchThread()) {
            // Safe to modify UI directly
            Point p = this.getLocation();
            Point point = new Point();
            point.setLocation(j, p.getY());
            this.setLocation(point);
            updateRoot();
        } else {
            // Schedule on EDT
            SwingUtilities.invokeLater(() -> {
                Point p = this.getLocation();
                Point point = new Point();
                point.setLocation(j, p.getY());
                this.setLocation(point);
                updateRoot();
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        alertIconLabel = new javax.swing.JLabel();
        accentBar = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(33, 100));

        jButton3.setFont(new java.awt.Font("Swis721 Ex BT", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 102, 102));
        jButton3.setText("X");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 4, Short.MAX_VALUE)
                                .addComponent(jButton3)));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(0, 59, Short.MAX_VALUE)));

        add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        titleLabel.setText("Success");
        titleLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(titleLabel);

        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        messageLabel.setText("Medical record saved successfully.");
        messageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(messageLabel);

        add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 0, 3));
        jPanel8.setPreferredSize(new java.awt.Dimension(80, 80));
        jPanel8.setLayout(new java.awt.BorderLayout());

        alertIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alertIconLabel.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/com/innovateyouth/jfreealerts/asset/check_32.png"))); // NOI18N
        alertIconLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alertIconLabel.setPreferredSize(new java.awt.Dimension(60, 16));
        jPanel8.add(alertIconLabel, java.awt.BorderLayout.CENTER);

        accentBar.setBackground(new java.awt.Color(255, 102, 102));
        accentBar.setPreferredSize(new java.awt.Dimension(8, 80));

        javax.swing.GroupLayout accentBarLayout = new javax.swing.GroupLayout(accentBar);
        accentBar.setLayout(accentBarLayout);
        accentBarLayout.setHorizontalGroup(
                accentBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE));
        accentBarLayout.setVerticalGroup(
                accentBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 84, Short.MAX_VALUE));

        jPanel8.add(accentBar, java.awt.BorderLayout.WEST);

        add(jPanel8, java.awt.BorderLayout.WEST);

        jProgressBar1.setBackground(new java.awt.Color(204, 204, 204));
        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setMaximum(5000);
        jProgressBar1.setValue(200);
        jProgressBar1.setMinimumSize(new java.awt.Dimension(10, 2));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 2));
        add(jProgressBar1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Run hide animation on EDT instead of background thread
        hideSelf();
    }// GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accentBar;
    private javax.swing.JLabel alertIconLabel;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private void applyProperties() {
        jPanel8.setBackground(new Color(220, 225, 220, 0));
        accentBar.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    private void addSelfToRoot() {
        adaptRootHeight();
        this.setBounds(x, y, width, height);
        root.add(this);
        updateRoot();
    }

    private synchronized void hideSelf() {
        if (!isRemoved) {
            // Use Timer for slide-out animation instead of Thread.sleep
            final double[] d = { 1.0 };
            final double ol = this.getLocation().getX();
            final double[] j = { this.getLocation().getX() };

            Timer hideTimer = new Timer(1, null);
            hideTimer.addActionListener(e -> {
                if (j[0] < root.getWidth()) {
                    updatePosition(j[0]);

                    // Handle acceleration motion
                    if (ol / j[0] <= 2) {
                        d[0] += 0.5;
                    } else {
                        d[0] -= 0.5;
                    }
                    if (d[0] <= 0) {
                        d[0] = 1;
                    }

                    j[0] += 0.1 * d[0];
                } else {
                    hideTimer.stop();
                    // Complete the hiding process on EDT
                    finishHiding();
                }
            });
            hideTimer.start();
        }
    }

    private void finishHiding() {
        List<Component> list = Arrays.asList(root.getComponents());
        int thisIndex = list.indexOf(this);
        root.remove(this);
        isRemoved = true;
        notifyClient();
        int count = root.getComponentCount();
        if (count < 1) {
            toggleRootVisibility(false);
        } else {
            // resetRootheight();
            list = Arrays.asList(root.getComponents());
            if (root.getComponent(0).getY() > height) {
                reOrderNotificationStack(list);
            } else if (thisIndex > 0) {
                reOrderNotificationStack(list.subList(thisIndex, count));
            }
        }
        updateRoot();
        root.firePropertyChange("HIDE", 0, height);
    }

    private void updateRoot() {
        checkEDT("updateRoot");
        root.revalidate();
        root.repaint();
    }

    private void resetRootheight() {
        if (root.getParent().getHeight() > (height + gap) * root.getComponentCount()) {
            root.setPreferredSize(new Dimension(root.getWidth(), root.getParent().getHeight()));
        }
    }

    private void reOrderNotificationStack(List<Component> list) {
        if (list.isEmpty())
            return;

        // Use Timer for smooth reordering animation
        final double[][] componentData = new double[list.size()][3]; // [originalY, currentY, acceleration]

        // Initialize component data
        for (int i = 0; i < list.size(); i++) {
            Component component = list.get(i);
            double oy = component.getLocation().getY();
            componentData[i][0] = oy; // original Y
            componentData[i][1] = oy; // current Y
            componentData[i][2] = 2.0; // acceleration
        }

        Timer reorderTimer = new Timer(1, null);
        reorderTimer.addActionListener(e -> {
            boolean allFinished = true;

            for (int i = 0; i < list.size(); i++) {
                Component component = list.get(i);
                double oy = componentData[i][0];
                double currentY = componentData[i][1];
                double d = componentData[i][2];
                double targetY = oy - (height + gap);

                if (currentY > targetY) {
                    allFinished = false;

                    // Handle acceleration motion
                    if (oy / currentY >= 2) {
                        d -= 0.5;
                    } else {
                        d += 0.5;
                    }
                    if (d <= 0) {
                        d = 1;
                    }

                    currentY -= 0.1 * d;
                    if (currentY < targetY) {
                        currentY = targetY;
                    }

                    componentData[i][1] = currentY;
                    componentData[i][2] = d;

                    Point p = component.getLocation();
                    Point point = new Point();
                    point.setLocation(p.getX(), currentY);
                    component.setLocation(point);
                }
            }

            updateRoot();

            if (allFinished) {
                reorderTimer.stop();
            }
        });
        reorderTimer.start();
    }

    private void notifyClient() {
        if (callBack != null) {
            callBack.run();
        }
    }

    private void adaptRootHeight() {
        if (root.getComponentCount() >= 1) {
            gap = 10;
            y = (height + gap) * root.getComponentCount();
            int newHeight = y + height + gap;
            if (root.getHeight() < newHeight) {
                root.setPreferredSize(
                        new Dimension(
                                root.getWidth(),
                                root.getHeight() + height + gap));
            }

        }
    }

    private void animateProgressBar() {
        Timer progressTimer = new Timer(1, null);
        final int[] progress = { 200 };

        progressTimer.addActionListener(e -> {
            if (progress[0] <= 5000) {
                jProgressBar1.setValue(progress[0]); // Now on EDT
                this.revalidate(); // Safe on EDT
                this.repaint(); // Safe on EDT
                progress[0]++;
            } else {
                progressTimer.stop();
                hideSelf(); // Start hide animation
            }
        });
        progressTimer.start();
    }

    private void toggleRootVisibility(boolean visible) {
        root.getParent().getParent().setVisible(true);
        root.getParent().setVisible(visible);
        root.setVisible(visible);
        root.getParent().getParent().getParent().revalidate();
        root.getParent().getParent().getParent().repaint();
    }

    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    // Debug method to verify EDT usage
    private void checkEDT(String methodName) {
        if (!SwingUtilities.isEventDispatchThread()) {
            System.err.println("WARNING: " + methodName + " called from non-EDT thread: "
                    + Thread.currentThread().getName());
        }
    }

}
