package SegundoParcial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Terminos_Condiciones extends JFrame implements ActionListener {
    // Atributos
    private JLabel ETitulo; 
    private JLabel EIcono;
    private JTextArea Condiciones;
    private JButton BtnContinuar;
    private JButton BtnNoAceptar;
    private JLabel EFooter;
    private JRadioButton Aceptar;
    
    // Colores RGB
    private static final Color AZUL_SUAVE  = new Color(80, 200, 120);
    private static final Color VERDE_ESMERALDA= new Color(168,232,140,255);
    public String Terms_Nombre;
    
    // Métodos
    public void iniciarPantalla(String nombre) {
        // Establecer el color de fondo
        getContentPane().setBackground(VERDE_ESMERALDA);
       Terms_Nombre=nombre;
        // Título
        ETitulo = new JLabel("Términos y Condiciones de Uso");
        ETitulo.setBounds(275, 10, 300, 20);
        ETitulo.setForeground(new Color(100, 160, 230)); // Color del texto
        add(ETitulo);
        
        // Cargar icono (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\kespinoza\\Documents\\NetBeansProjects\\EcoNova_Soluciones\\build\\classes\\SegundoParcial\\Logo.jpg");
        // Colocar icono en el JFrame
        setIconImage(icon);
        
        ImageIcon logo = new ImageIcon("C:\\Users\\kespinoza\\Documents\\NetBeansProjects\\EcoNova_Soluciones\\build\\classes\\SegundoParcial\\Logo.jpg");
       
        Image image = logo.getImage();
        Image scaledImage = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        // Crear el JLabel con la imagen redimensionada
        EIcono = new JLabel(scaledIcon);
        EIcono.setBounds(590, 10, 200, 100);
        
        add(EIcono);

        // Etiqueta y caja de texto para ingresar nombre
        Condiciones = new JTextArea("   1.) Aceptación de los Términos\n" +
"   Al usar los servicios de EcoNova Solutions, usted acepta estos términos. \n" +
"   Si no está de acuerdo, debe dejar de usar nuestros servicios.\n" +
"\n" +
"   2.) Responsabilidad por la Información\n" +
"   Usted es responsable de la información que crea y gestiona a través de \n" +
"   nuestros servicios. Asume la responsabilidad por su exactitud y veracidad.\n" +
"\n" +
"   3.) Confidencialidad\n" +
"   La información que gestione será confidencial. No compartiremos su \n" +
"   información con terceros sin su consentimiento. Usted también debe mantener \n" +
"   la confidencialidad.\n" +
"\n" +
"   4.) Limitación de Responsabilidad\n" +
"   EcoNova Solutions no es responsable por daños derivados del mal uso de la \n" +
"   información generada. Cualquier decisión basada en dicha información es \n" +
"   su responsabilidad.\n" +
"\n" +
"   5.) Ley Aplicable\n" +
"   Estos términos se regirán por las leyes locales de la jurisdicción \n" +
"   correspondiente.\n" +
"\n" +
"   Al aceptar estos términos, usted declara haber leído y comprendido su \n" +
"   contenido y se compromete a cumplirlos.");
        Condiciones.setBounds(30, 50, 550, 400);
        add(Condiciones);
        Condiciones.setEditable(false);

      
        // Botón para aceptar
        Aceptar = new JRadioButton(" Yo "+nombre+" Acepto los términos y condiciones");
        Aceptar.setBounds(50, 460, 500, 20);
        Aceptar.setBackground(VERDE_ESMERALDA); // Color del texto
        add(Aceptar);
        
        
        // Botón para continuar
        BtnContinuar = new JButton("Continuar");
        BtnContinuar.setBounds(425, 490, 200, 20);
        BtnContinuar.setEnabled(false);
        add(BtnContinuar);
        
         // Botón para continuar
        BtnNoAceptar = new JButton("No Acepto");
        BtnNoAceptar.setBounds(175, 490, 200, 20);
        add(BtnNoAceptar);
        
        
        BtnContinuar.addActionListener(this);
        Aceptar.addActionListener(this);
        BtnNoAceptar.addActionListener(this);
        
        // Footer con datos de negocio y estudiante
        EFooter = new JLabel("EcoNova Soluciones - Keneth Lennin Espinoza Xoyon - Curso Programacion II - Sección B", JLabel.CENTER);
        EFooter.setBounds(110, 530, 550, 20);
        EFooter.setForeground(AZUL_SUAVE); // Color del texto
        add(EFooter);

        // Cierra el formulario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         
        if (e.getSource() == Aceptar) {
           
            if (Aceptar.isSelected()) {
               BtnContinuar.setEnabled(true);
               BtnNoAceptar.setEnabled(false);
            }
             else {
               BtnContinuar.setEnabled(false);
               BtnNoAceptar.setEnabled(true);
            }
        }
        if (e.getSource() == BtnContinuar) {
            this.setVisible(false);
            PantallaPrincipal formulario = new PantallaPrincipal();
            formulario.setBounds(200, 10, 800, 620);
            formulario.setLayout(null);
            formulario.iniciarPantalla(Terms_Nombre);
            formulario.setVisible(true);
        }
        
        if (e.getSource() == BtnNoAceptar) {
            this.setVisible(false);
            PantallaBienvenida formulario = new PantallaBienvenida();
            formulario.setBounds(200, 10, 800, 600);
            formulario.setLayout(null);
            formulario.iniciarPantalla();
            formulario.setVisible(true);
        }
    }
        
    

    // Método principal
    public static void main(String[] args) {
        
    }
}
