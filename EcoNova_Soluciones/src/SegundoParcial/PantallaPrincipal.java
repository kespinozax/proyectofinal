package SegundoParcial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
public class PantallaPrincipal extends JFrame implements ActionListener {
    // Atributos
    private JLabel ETitulo; 
    private JLabel EIcono;
    private JLabel ENombres;
    private JTextField TxtNombres;
    private JLabel EApellidos;
    private JTextField TxtApellidos;
    private JLabel EAntiguedad;
    private JComboBox CAntiguedad;
    private JLabel EDepartamento;
    private JComboBox CDepartamento;
    private JLabel EResultado;
    private JButton BtnLimpiar;
    private JButton BtnCalcular;
    private JLabel EFooter;
    private JLabel EUsuario;
    
    
    private JMenuBar mb;
    private JMenu menu1,menu3;
    private JMenuItem mi1,mi2,mi3,mi31,menu2;
    
    // Colores RGB
    private static final Color AZUL_SUAVE  = new Color(80, 200, 120);
    private static final Color VERDE_ESMERALDA= new Color(168,232,140,255);
    private Color colorSeleccionado;
    public String Terms_Nombre;
    // Métodos
    public void cambiarColorFondo(Color color) {
        getContentPane().setBackground(color);
        repaint(); // Actualizar el formulario para que se vea el cambio
    }
    public void iniciarPantalla(String nombre) {
        Terms_Nombre=nombre;
        
        setLayout(null);
        mb=new JMenuBar();
        setJMenuBar(mb);
        menu2=new JMenuItem("Inicio");
        mb.add(menu2);
        menu2.addActionListener(this);
        menu2.setBounds(0, 0, 10, 20);
        
        menu1=new JMenu("Opciones");
        mb.add(menu1);
        
        menu3=new JMenu("Acerca de...");
        mb.add(menu3);
        mi1=new JMenuItem("Cambiar color de fondo");
        mi1.addActionListener(this);
        menu1.add(mi1);
        
        mi2=new JMenuItem("Cambiar color de Letra");
        mi2.addActionListener(this);
        menu1.add(mi2);
        
        mi31=new JMenuItem("Desarrollado por Keneth Espinoza || Ingenieria || Seccion B");
        menu3.add(mi31);
        mi31.disable();
        
        // Establecer el color de fondo
        getContentPane().setBackground(VERDE_ESMERALDA);

        // Título
        ETitulo = new JLabel("Calculo de Vacaciones");
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
 
        EUsuario = new JLabel("Bienvenido "+ nombre);
        EUsuario.setBounds(600, 110, 200, 20);
        add(EUsuario);

        ENombres = new JLabel("Introduzca los nombres:");
        ENombres.setBounds(200, 200, 200, 20);
        ENombres.setForeground(new Color(36,130,170,255)); // Color del texto
        add(ENombres);
        
        TxtNombres = new JTextField();
        TxtNombres.setBounds(200, 225, 200, 20);
        add(TxtNombres);
        
        EApellidos = new JLabel("Introduzca los apellidos:");
        EApellidos.setBounds(200, 275, 200, 20);
        EApellidos.setForeground(new Color(36,130,170,255)); // Color del texto
        add(EApellidos);
        
        TxtApellidos = new JTextField();
        TxtApellidos.setBounds(200, 300, 200, 20);
        add(TxtApellidos);
      
        
        EDepartamento = new JLabel("Seleccione el departamento:");
        EDepartamento.setBounds(400, 200, 200, 20);
        EDepartamento.setForeground(new Color(36,130,170,255)); // Color del texto
        add(EDepartamento);
        
        CDepartamento = new JComboBox();
        CDepartamento.setBounds(400, 225, 200, 20);
        add(CDepartamento);
        CDepartamento.addItem("");
        CDepartamento.addItem("Atencion al Cliente");
        CDepartamento.addItem("Logistica");
        CDepartamento.addItem("Gerencia");
        
        EAntiguedad = new JLabel("Seleccione la antiguedad:");
        EAntiguedad.setBounds(400, 275, 200, 20);
        EAntiguedad.setForeground(new Color(36,130,170,255)); // Color del texto
        add(EAntiguedad);
          
        CAntiguedad = new JComboBox();
        CAntiguedad.setBounds(400, 300, 200, 20);
        add(CAntiguedad);
        CAntiguedad.addItem("");
        CAntiguedad.addItem("1 Año");
        CAntiguedad.addItem("2 a 6 Años");
        CAntiguedad.addItem("7 Años");
        
        
        EResultado = new JLabel();
        EResultado.setBounds(250, 350, 405, 20);
        add(EResultado);
        
        
        // Botón para continuar
        BtnLimpiar = new JButton("Limpiar");
        BtnLimpiar.setBounds(200, 490, 200, 20);
        add(BtnLimpiar);
        
        BtnCalcular = new JButton("Calcular");
        BtnCalcular.setBounds(400, 490, 200, 20);
        add(BtnCalcular);
        
        
        BtnLimpiar.addActionListener(this);
        BtnCalcular.addActionListener(this);
 
        
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
        Container f=this.getContentPane();
        
        if (e.getSource()==mi1) {
           colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un Color", colorSeleccionado);
            f.setBackground(colorSeleccionado);   
        }
        if (e.getSource()==mi2) {
           colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un Color", colorSeleccionado);
    
        ENombres.setForeground(colorSeleccionado);   
        EApellidos.setForeground(colorSeleccionado);   
        ETitulo.setForeground(colorSeleccionado);   
        EAntiguedad.setForeground(colorSeleccionado);   
        EDepartamento.setForeground(colorSeleccionado);   
        EFooter.setForeground(colorSeleccionado);   
        EUsuario.setForeground(colorSeleccionado);   
        }
       if (e.getSource()==menu2) {
           this.setVisible(false);
        PantallaBienvenida formulario = new PantallaBienvenida();
        formulario.setBounds(200, 10, 800, 600);
        formulario.setLayout(null);
        formulario.iniciarPantalla();
        formulario.setVisible(true);
        }
       
        if (e.getSource() == BtnLimpiar) {
       TxtNombres.setText("");
       TxtApellidos.setText("");
       EResultado.setText("");
       CAntiguedad.setSelectedItem("");
       CDepartamento.setSelectedItem("");
       
       }
       if (e.getSource() == BtnCalcular) {
           EResultado.setForeground(new Color(0,0,0)); // Color del texto
           if(CDepartamento.getSelectedItem()=="Atencion al Cliente"){
                if(CAntiguedad.getSelectedItem()=="1 Año"){
                    EResultado.setText("6 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="2 a 6 Años"){
                    EResultado.setText("14 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="7 Años"){
                    EResultado.setText("20 dias de Vacaciones");
                }
            }else if (CDepartamento.getSelectedItem()=="Logistica"){
                if(CAntiguedad.getSelectedItem()=="1 Año"){
                    EResultado.setText("7 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="2 a 6 Años"){
                    EResultado.setText("15 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="7 Años"){
                    EResultado.setText("22 dias de Vacaciones");
                }
            }else if (CDepartamento.getSelectedItem()=="Gerencia"){
                if(CAntiguedad.getSelectedItem()=="1 Año"){
                    EResultado.setText("10 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="2 a 6 Años"){
                    EResultado.setText("20 dias de Vacaciones");
                }else if (CAntiguedad.getSelectedItem()=="7 Años"){
                    EResultado.setText("30 dias de Vacaciones");
                }
            }
           else{
                    EResultado.setText("Llene los campos obligatorios");
                    EResultado.setForeground(new Color(255,0,0)); // Color del texto
                }
       }   
       
       
       
       
    }
        
    

    // Método principal
    public static void main(String[] args) {
        
    }
}
