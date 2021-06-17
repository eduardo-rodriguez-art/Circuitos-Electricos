/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerialProc;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ERROR;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.Logger.Level.ERROR;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Prueba extends JPanel implements ActionListener {

    private static final String ON = "On";
    private static final String OFF = "Off";
    private static final String TURN_ON = "1";
    private static final String TURN_OFF = "0";

    JButton switchOnButton;
    JButton switchOffButton;
    JLabel label;
    JFrame frame;
    JButton leerDatos;
    /**
     * el output stream del puerto
     */
    private OutputStream output = null;

    SerialPort serialPort;
    private final String PORT_NAME = "/dev/cu.usbmodem1d11";
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    JTextArea area;

    public void createAndShowGUI() {
        area = new JTextArea("Aqui se presentará la información que proviene de la placa");
        
        JFrame frame = new JFrame("Ejemplo de arduino");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        switchOnButton = new JButton(ON);
        switchOnButton.setText("Conectar");
        switchOffButton = new JButton(OFF);
        switchOffButton.setText("OFF");
        switchOffButton.setEnabled(false);
        leerDatos = new JButton("Leer Datos");
        
        JPanel newContentPane = new JPanel();
        //newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setLayout(new GridLayout(4,1));
        frame.add(switchOnButton);
        frame.add(switchOffButton);
        frame.add(area);
        frame.add(leerDatos);
        
        leerDatos.addActionListener(this);
        switchOnButton.addActionListener(this);
        switchOffButton.addActionListener(this);
        frame.pack();
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
    
    public void init() {
        switchOnButton = new JButton(ON);
        switchOnButton.setText("Conectar");
        switchOffButton = new JButton(OFF);
        switchOffButton.setText("OFF");
        switchOffButton.setEnabled(false);

        label = new JLabel("Calculo de Corriente desde Arduino");

        switchOnButton.addActionListener(this);
        switchOffButton.addActionListener(this);

        add(label);
        add(switchOnButton);
        add(switchOffButton);
        setSize(400,290);
        
        initializeArduinoConnection();
    }

    public void initializeArduinoConnection() {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // iteracion a traves por los puertos
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            if (PORT_NAME.equals(currPortId.getName())) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {

            return;
        }

        try {
            // abre el puerto serie
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            // asigna parametros del puerto
            serialPort.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();

        } catch (Exception e) {
            showError(e.getMessage());
            System.exit(ERROR);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ON.equals(e.getActionCommand())) {
            switchOnButton.setEnabled(false);
            switchOffButton.setEnabled(true);
            
            try {
                sendData(TURN_ON);
            }catch (IOException ex) {
                
            }

        }else if(e.getSource().equals(leerDatos)){
            System.out.println("Leer datos");
        } else if(e.getSource().equals(switchOnButton)){
            System.out.println("Encender");
            switchOnButton.setEnabled(false);
            switchOffButton.setEnabled(true);
            
            try {
                sendData(TURN_ON);
            }catch (IOException ex) {
                
            }
        }else if(e.getSource().equals(switchOffButton)){
            switchOnButton.setEnabled(true);
            switchOffButton.setEnabled(false);
            try {
                sendData(TURN_OFF);
            } catch (IOException ex) {
                
            }
        }
        /*else {
            switchOnButton.setEnabled(true);
            switchOffButton.setEnabled(false);
            try {
                sendData(TURN_OFF);
            } catch (IOException ex) {
                
            }
        }*/

    }

    private void sendData(String data) throws IOException {

        output.write(data.getBytes());
    }

    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(frame,errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
    }

}
