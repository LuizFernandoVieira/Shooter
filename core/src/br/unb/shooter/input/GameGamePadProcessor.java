package br.unb.shooter.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;

import br.unb.shooter.gamepad.XBox360Pad;

public class GameGamePadProcessor extends ControllerAdapter {

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        System.out.println("buttonDown");
        
        // if(controller.getButton(XBox360Pad.BUTTON_Y)) {
        // System.out.println("Y");
        // }
        // if(controller.getButton(XBox360Pad.BUTTON_X)) {
        // System.out.println("X");
        // }
        // if(controller.getButton(XBox360Pad.BUTTON_B)) {
        // System.out.println("B");
        // }
        // if(controller.getButton(XBox360Pad.BUTTON_A)) {
        // System.out.println("A");
        // }
        // if(controller.getButton(XBox360Pad.BUTTON_LB)) {
        // System.out.println("LB");
        // }
        // if(controller.getButton(XBox360Pad.BUTTON_RB)) {
        // System.out.println("RB");
        // }

        if (buttonCode == XBox360Pad.BUTTON_Y) {
            System.out.println("Y");
        }
        if (buttonCode == XBox360Pad.BUTTON_X) {

        }
        if (buttonCode == XBox360Pad.BUTTON_A) {

        }
        if (buttonCode == XBox360Pad.BUTTON_B) {

        }
        if (buttonCode == XBox360Pad.BUTTON_LB) {

        }
        if (buttonCode == XBox360Pad.BUTTON_RT) {

        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        
        System.out.println("buttonUp");
        
        if (buttonCode == XBox360Pad.BUTTON_Y) {

        }
        if (buttonCode == XBox360Pad.BUTTON_X) {

        }
        if (buttonCode == XBox360Pad.BUTTON_A) {

        }
        if (buttonCode == XBox360Pad.BUTTON_B) {

        }
        if (buttonCode == XBox360Pad.BUTTON_LB) {

        }
        if (buttonCode == XBox360Pad.BUTTON_RT) {

        }
        
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        
        System.out.println("axisMoved");

        // if(controller.getAxis(XBox360Pad.AXIS_LEFT_X) > 0.2f ||
        // controller.getAxis(XBox360Pad.AXIS_LEFT_X) < -0.2f) {
        // System.out.println("AXIS_LEFT_X");
        // }
        // if(controller.getAxis(XBox360Pad.AXIS_LEFT_Y) > 0.2f ||
        // controller.getAxis(XBox360Pad.AXIS_LEFT_Y) < -0.2f) {
        // System.out.println("AXIS_LEFT_Y");
        // }
        // if(controller.getAxis(XBox360Pad.AXIS_RIGHT_X) > 0.2f ||
        // controller.getAxis(XBox360Pad.AXIS_RIGHT_X) < -0.2f) {
        // System.out.println("AXIS_RIGHT_X");
        // }
        // if(controller.getAxis(XBox360Pad.AXIS_RIGHT_Y) > 0.2f ||
        // controller.getAxis(XBox360Pad.AXIS_RIGHT_Y) < -0.2f) {
        // System.out.println("AXIS_RIGHT_Y");
        // }

        if (axisCode == XBox360Pad.AXIS_LEFT_X) {

        }
        if (axisCode == XBox360Pad.AXIS_LEFT_Y) {

        }
        if (axisCode == XBox360Pad.AXIS_RIGHT_X) {

        }
        if (axisCode == XBox360Pad.AXIS_RIGHT_Y) {

        }

        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }
}
