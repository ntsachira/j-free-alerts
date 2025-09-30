/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alertcontainer;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.BeanDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author Sachira Jayawardana
 */
public class JNotificationPaneBeanInfo extends SimpleBeanInfo {
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(JNotificationPane.class);
    }


  @Override
    public Image getIcon(int iconKind) {
        return Toolkit
                .getDefaultToolkit()
                .getImage(
                        getClass()
                        .getResource("/alertcontainer/play_icon.png"));
    }
}
