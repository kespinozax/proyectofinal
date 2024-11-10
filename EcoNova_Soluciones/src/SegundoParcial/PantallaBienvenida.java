package SegundoParcial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaBienvenida extends JFrame implements ActionListener {
    // Atributos

    private JLabel ETitulo; 
    private JLabel EIcono;
    private JLabel ENombre;
    private JTextField TxtNombre;
    private JButton BtnContinuar;
    private JLabel EFooter;
    private JLabel Error;
    
    // Colores RGB
    private static final Color AZUL_SUAVE  = new Color(80, 200, 120);
    private static final Color VERDE_ESMERALDA= new Color(168,232,140,255);
    
    // Métodos
    public void iniciarPantalla() {
        
        
        // Establecer el color de fondo
        getContentPane().setBackground(VERDE_ESMERALDA);

        // Título
        ETitulo = new JLabel("Bienvenido a EcoNova Soluciones");
        ETitulo.setBounds(275, 10, 300, 20);
        ETitulo.setForeground(new Color(100, 160, 230)); // Color del texto
        add(ETitulo);
        
        // Cargar icono (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\KLEX\\Desktop\\EcoNova_Soluciones\\build\\classes\\SegundoParcial\\Logo.jpg");
        // Colocar icono en el JFrame
        setIconImage(icon);
        
        ImageIcon logo = new ImageIcon("C:\\Users\\KLEX\\Desktop\\EcoNova_Soluciones\\build\\classes\\SegundoParcial\\Logo.jpg");
       
        Image image = logo.getImage();
        Image scaledImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        
        // Crear el JLabel con la imagen redimensionada
        EIcono = new JLabel(scaledIcon);
        EIcono.setBounds(0, 40, 800, 400);
        
        add(EIcono);

        // Etiqueta y caja de texto para ingresar nombre
        ENombre = new JLabel("Introduce tu nombre:");
        ENombre.setBounds(50, 470, 200, 20);
        ENombre.setForeground(new Color(36,130,170,255)); // Color del texto
        add(ENombre);
        
        TxtNombre = new JTextField();
        TxtNombre.setBounds(200, 470, 200, 20);
        add(TxtNombre);
        
        Error = new JLabel();
        Error.setBounds(200, 490, 200, 20);
        Error.setForeground(new Color(255,0,0)); // Color del texto
        add(Error);
        Error.setVisible(false);
        
        // Botón para continuar
        BtnContinuar = new JButton("Continuar");
        BtnContinuar.setBounds(450, 470, 200, 20);
        BtnContinuar.setBackground(AZUL_SUAVE); // Color del texto
        
        add(BtnContinuar);
        
        BtnContinuar.addActionListener(this);

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
        if (e.getSource() == BtnContinuar) {
            String nombre = TxtNombre.getText();
            if (nombre.isEmpty()) {
                // Si el nombre está vacío, mostrar un mensaje de error
                Error.setText("Error: Introduce tu nombre.");
                Error.setVisible(true);
            } else {
                
            this.setVisible(false);
            Terminos_Condiciones terms = new Terminos_Condiciones();
            terms.setBounds(200, 10, 800, 600);
            terms.setLayout(null);
            terms.iniciarPantalla(nombre);
            terms.setVisible(true);  
            }
        }
        
        
    }

    // Método principal
    public static void main(String[] args) {
        PantallaBienvenida formulario = new PantallaBienvenida();
        formulario.setBounds(200, 10, 800, 600);
        formulario.setLayout(null);
        formulario.iniciarPantalla();
        formulario.setVisible(true);
    }
}
