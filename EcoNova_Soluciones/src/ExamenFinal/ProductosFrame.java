package ExamenFinal;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.sql.PreparedStatement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ProductosFrame extends JFrame {
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private HashSet<Integer> filasModificadas;
    private boolean actualizandoModelo = false;
    private JTextField filtroTextField;
    private JComboBox<String> filtroComboBox;

    public ProductosFrame() {
        setTitle("Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para el filtro
        JPanel filtroPanel = new JPanel();
        filtroPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Crear el combo box para seleccionar el filtro
        filtroComboBox = new JComboBox<>(new String[]{"Código", "Nombre", "Precio", "Cantidad", "Fecha Vencimiento"});
        filtroPanel.add(filtroComboBox);

        // Crear el text field para escribir el filtro
        filtroTextField = new JTextField(15);
        filtroPanel.add(filtroTextField);

        // Acción del filtro en el textfield
        filtroTextField.addActionListener(e -> filtrarTabla());

        // Añadir el panel de filtros en la parte superior
        add(filtroPanel, BorderLayout.NORTH);

        // Inicialización del modelo de la tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Fecha Vencimiento", "Acciones"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        tablaProductos = new JTable(modeloTabla);
        filasModificadas = new HashSet<>();

        modeloTabla.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (actualizandoModelo) return;

                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column >= 1 && column <= 4) {
                    filasModificadas.add(row);
                    actualizandoModelo = true;
                    if (modeloTabla.getValueAt(row, 5) == "Guardar") {
                    } else {
                        modeloTabla.setValueAt("Actualizar", row, 5);
                    }
                    actualizandoModelo = false;
                }
            }
        });

        tablaProductos.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
        tablaProductos.getColumn("Acciones").setCellEditor(new ButtonEditor(new JCheckBox()));

        cargarProductos();

        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
    }

    private void cargarProductos() {
        // Vaciar la tabla antes de cargar los nuevos productos
        modeloTabla.setRowCount(0);

        try (ResultSet rs = ConexionBD.listarProductos()) {
            while (rs != null && rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getString("codigoProducto"),
                        rs.getString("nombreProducto"),
                        rs.getDouble("precioUnitario"),
                        rs.getInt("cantidadProducto"),
                        rs.getDate("fechaVencimiento"),
                        "Eliminar"  // O un JButton si prefieres
                });
            }
            modeloTabla.addRow(new Object[]{
                    "",
                    "",
                    "",
                    "",
                    "",
                    "Guardar"  // O un JButton si prefieres
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void filtrarTabla() {
    String filtro = filtroTextField.getText().toLowerCase();  // Obtener el texto del filtro
    String columnaSeleccionada = (String) filtroComboBox.getSelectedItem();  // Obtener el campo seleccionado en el ComboBox

    // Vaciar la tabla antes de aplicar el filtro
    modeloTabla.setRowCount(0);

    // Llamar al método listarProductos con los parámetros de columna y filtro
    ResultSet rs = ConexionBD.filtrarProductos(columnaSeleccionada, filtro);

    try {
        while (rs != null && rs.next()) {
            modeloTabla.addRow(new Object[]{
                    rs.getString("codigoProducto"),
                    rs.getString("nombreProducto"),
                    rs.getDouble("precioUnitario"),
                    rs.getInt("cantidadProducto"),
                    rs.getDate("fechaVencimiento"),
                    "Eliminar"
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al mostrar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


  
    private void actualizarProductoDeTabla(String codigoProducto, int fila) {
    String nombreProducto = (String) modeloTabla.getValueAt(fila, 1);

    // Convertir el precio a Double si está en formato String
    Object precioObj = modeloTabla.getValueAt(fila, 2);
    double precio;
    if (precioObj instanceof Double) {
        precio = (Double) precioObj;
    } else if (precioObj instanceof String) {
        precio = Double.parseDouble((String) precioObj);
    } else {
        JOptionPane.showMessageDialog(this, "Formato de precio incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir la cantidad a Integer si está en formato String
    Object cantidadObj = modeloTabla.getValueAt(fila, 3);
    int cantidad;
    if (cantidadObj instanceof Integer) {
        cantidad = (Integer) cantidadObj;
    } else if (cantidadObj instanceof String) {
        cantidad = Integer.parseInt((String) cantidadObj);
    } else {
        JOptionPane.showMessageDialog(this, "Formato de cantidad incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir la fecha de vencimiento a Date si está en formato String
    Object fechaObj = modeloTabla.getValueAt(fila, 4);
    Date fechaVencimiento;
    if (fechaObj instanceof Date) {
        fechaVencimiento = (Date) fechaObj;
    } else if (fechaObj instanceof String) {
        fechaVencimiento = Date.valueOf((String) fechaObj); // Convertir String a Date
    } else {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Llamada para actualizar el producto en la base de datos
    ConexionBD.actualizarProducto(codigoProducto, nombreProducto, precio, cantidad, fechaVencimiento);

    // Actualizar la tabla y reflejar los cambios
    modeloTabla.fireTableDataChanged();  // Actualiza la tabla para que se refleje el cambio
    cargarProductos();

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Producto actualizado correctamente", "Actualización", JOptionPane.INFORMATION_MESSAGE);
    
    // Cerrar el formulario actual
    this.dispose();
    
    // Reabrir el formulario para mostrar los productos nuevamente
    SwingUtilities.invokeLater(() -> {
        new ProductosFrame().setVisible(true);
    });
}

private void guardarProductoDeTabla(int fila) {
    String nombreProducto = (String) modeloTabla.getValueAt(fila, 1);

    // Convertir el precio a Double si está en formato String
    Object precioObj = modeloTabla.getValueAt(fila, 2);
    double precio;
    if (precioObj instanceof Double) {
        precio = (Double) precioObj;
    } else if (precioObj instanceof String) {
        precio = Double.parseDouble((String) precioObj);
    } else {
        JOptionPane.showMessageDialog(this, "Formato de precio incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir la cantidad a Integer si está en formato String
    Object cantidadObj = modeloTabla.getValueAt(fila, 3);
    int cantidad;
    if (cantidadObj instanceof Integer) {
        cantidad = (Integer) cantidadObj;
    } else if (cantidadObj instanceof String) {
        cantidad = Integer.parseInt((String) cantidadObj);
    } else {
        JOptionPane.showMessageDialog(this, "Formato de cantidad incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir la fecha de vencimiento a Date si está en formato String
    Object fechaObj = modeloTabla.getValueAt(fila, 4);
    Date fechaVencimiento;
    if (fechaObj instanceof Date) {
        fechaVencimiento = (Date) fechaObj;
    } else if (fechaObj instanceof String) {
        fechaVencimiento = Date.valueOf((String) fechaObj); // Convertir String a Date
    } else {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Llamada para guardar el producto en la base de datos
    ConexionBD.insertarProducto(nombreProducto, precio, cantidad, fechaVencimiento);

    // Actualizar la tabla y reflejar los cambios
    modeloTabla.fireTableDataChanged();  // Actualiza la tabla para que se refleje el cambio
    cargarProductos();

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Producto guardado correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
    
    // Cerrar el formulario actual
    this.dispose();
    
    // Reabrir el formulario para mostrar los productos nuevamente
    SwingUtilities.invokeLater(() -> {
        new ProductosFrame().setVisible(true);
    });
}

    private void eliminarProductoDeTabla(String codigoProducto, int fila) {
        ConexionBD.eliminarProducto(codigoProducto);
        modeloTabla.removeRow(fila);
        JOptionPane.showMessageDialog(this, "Producto eliminado correctamente", "Eliminación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String codigoProducto;
        private int fila;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String accion = button.getText();
                    if (accion.equals("Eliminar")) {
                        eliminarProductoDeTabla(codigoProducto, fila);
                    } else if (accion.equals("Actualizar")) {
                        actualizarProductoDeTabla(codigoProducto, fila);
                    } else if (accion.equals("Guardar")) {
                        guardarProductoDeTabla(fila);
                    }
                    fireEditingStopped();
                    tablaProductos.repaint();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            codigoProducto = (String) table.getValueAt(row, 0);
            fila = row;
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductosFrame().setVisible(true);
        });
    }
}
