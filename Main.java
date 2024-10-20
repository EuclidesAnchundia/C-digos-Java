import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    // Lista para almacenar los historiales médicos
    private ArrayList<HistorialMedico> historiales = new ArrayList<>();
    
    // Variable para saber si estamos actualizando un historial existente
    private HistorialMedico historialActual = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Ingreso de Historial Médico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campos de texto
        JTextField idHistorialField = new JTextField(20);
        JTextField fechaField = new JTextField(20);
        JTextField idPacienteField = new JTextField(20);
        JTextField nombrePacienteField = new JTextField(20);
        JTextField apellidoPacienteField = new JTextField(20);
        JTextField idTratamientoField = new JTextField(20);
        JTextField descripcionTratamientoField = new JTextField(20);
        JTextField idRecetaField = new JTextField(20);
        JTextField medicamentoField = new JTextField(20);

        // Botones
        JButton guardarButton = new JButton("Guardar Historial");
        JButton verButton = new JButton("Ver Historiales");
        JButton actualizarButton = new JButton("Actualizar Historial");
        JButton eliminarButton = new JButton("Eliminar Historial");

        // Añadir componentes al frame
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(new JLabel("ID del Historial Médico:"), gbc);
        gbc.gridx = 1;
        frame.add(idHistorialField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("Fecha de Actualización (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        frame.add(fechaField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("ID del Paciente:"), gbc);
        gbc.gridx = 1;
        frame.add(idPacienteField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("Nombre del Paciente:"), gbc);
        gbc.gridx = 1;
        frame.add(nombrePacienteField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("Apellido del Paciente:"), gbc);
        gbc.gridx = 1;
        frame.add(apellidoPacienteField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("ID del Tratamiento:"), gbc);
        gbc.gridx = 1;
        frame.add(idTratamientoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("Descripción del Tratamiento:"), gbc);
        gbc.gridx = 1;
        frame.add(descripcionTratamientoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("ID de la Receta:"), gbc);
        gbc.gridx = 1;
        frame.add(idRecetaField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(new JLabel("Medicamento Recetado:"), gbc);
        gbc.gridx = 1;
        frame.add(medicamentoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(guardarButton, gbc);
        gbc.gridx = 1;
        frame.add(verButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        frame.add(actualizarButton, gbc);
        gbc.gridx = 1;
        frame.add(eliminarButton, gbc);

        // Acción del botón "Guardar"
        guardarButton.addActionListener(e -> {
            try {
                if (historialActual == null) {
                    // Crear un nuevo historial
                    HistorialMedico historial = new HistorialMedico();
                    historial.idHistorial = Integer.parseInt(idHistorialField.getText());
                    historial.fechaDeActualizacion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaField.getText());
                    
                    Paciente paciente = new Paciente();
                    paciente.idPaciente = Integer.parseInt(idPacienteField.getText());
                    paciente.nombre = nombrePacienteField.getText();
                    paciente.apellido = apellidoPacienteField.getText();
                    historial.paciente = paciente;

                    Tratamiento tratamiento = new Tratamiento();
                    tratamiento.idTratamiento = Integer.parseInt(idTratamientoField.getText());
                    tratamiento.tipoTratamiento = descripcionTratamientoField.getText();
                    historial.tratamiento = tratamiento;

                    Receta receta = new Receta();
                    receta.idReceta = Integer.parseInt(idRecetaField.getText());
                    receta.medicamento = medicamentoField.getText();
                    historial.receta = receta;

                    historiales.add(historial); // Guardar el historial
                    JOptionPane.showMessageDialog(frame, "Historial guardado con éxito.");
                } else {
                    // Actualizar el historial existente
                    historialActual.fechaDeActualizacion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaField.getText());
                    historialActual.paciente.idPaciente = Integer.parseInt(idPacienteField.getText());
                    historialActual.paciente.nombre = nombrePacienteField.getText();
                    historialActual.paciente.apellido = apellidoPacienteField.getText();
                    historialActual.tratamiento.idTratamiento = Integer.parseInt(idTratamientoField.getText());
                    historialActual.tratamiento.tipoTratamiento = descripcionTratamientoField.getText();
                    historialActual.receta.idReceta = Integer.parseInt(idRecetaField.getText());
                    historialActual.receta.medicamento = medicamentoField.getText();
                    
                    JOptionPane.showMessageDialog(frame, "Historial actualizado con éxito.");
                    historialActual = null; // Limpiar la referencia después de actualizar
                }

                // Limpiar campos después de guardar o actualizar
                idHistorialField.setText("");
                fechaField.setText("");
                idPacienteField.setText("");
                nombrePacienteField.setText("");
                apellidoPacienteField.setText("");
                idTratamientoField.setText("");
                descripcionTratamientoField.setText("");
                idRecetaField.setText("");
                medicamentoField.setText("");

            } catch (NumberFormatException | ParseException ex) {
                JOptionPane.showMessageDialog(frame, "Error en la entrada de datos. Verifica los campos.");
            }
        });

        // Acción del botón "Ver Historiales"
        verButton.addActionListener(e -> {
            StringBuilder historialInfo = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (HistorialMedico historial : historiales) {
                historialInfo.append("ID Historial: ").append(historial.idHistorial).append("\n");
                historialInfo.append("Fecha: ").append(dateFormat.format(historial.fechaDeActualizacion)).append("\n");
                historialInfo.append("Paciente: ").append(historial.paciente.nombre).append(" ")
                             .append(historial.paciente.apellido).append("\n");
                historialInfo.append("Tratamiento: ").append(historial.tratamiento.tipoTratamiento).append("\n");
                historialInfo.append("Receta: ").append(historial.receta.medicamento).append("\n\n");
            }
            JOptionPane.showMessageDialog(frame, historialInfo.toString(), "Historiales Médicos", JOptionPane.INFORMATION_MESSAGE);
        });

        // Acción del botón "Actualizar Historial"
        actualizarButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(frame, "Ingresa el ID del historial a actualizar:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    for (HistorialMedico historial : historiales) {
                        if (historial.idHistorial == id) {
                            historialActual = historial; // Guardar referencia del historial a actualizar
                            idHistorialField.setText(String.valueOf(historial.idHistorial));
                            fechaField.setText(new SimpleDateFormat("yyyy-MM-dd").format(historial.fechaDeActualizacion));
                            idPacienteField.setText(String.valueOf(historial.paciente.idPaciente));
                            nombrePacienteField.setText(historial.paciente.nombre);
                            apellidoPacienteField.setText(historial.paciente.apellido);
                            idTratamientoField.setText(String.valueOf(historial.tratamiento.idTratamiento));
                            descripcionTratamientoField.setText(historial.tratamiento.tipoTratamiento);
                            idRecetaField.setText(String.valueOf(historial.receta.idReceta));
                            medicamentoField.setText(historial.receta.medicamento);
                            JOptionPane.showMessageDialog(frame, "Datos cargados para actualizar.");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "ID no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ID inválido.");
                }
            }
        });

        // Acción del botón "Eliminar Historial"
        eliminarButton.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(frame, "Ingresa el ID del historial a eliminar:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    for (HistorialMedico historial : historiales) {
                        if (historial.idHistorial == id) {
                            historiales.remove(historial);
                            JOptionPane.showMessageDialog(frame, "Historial eliminado.");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "ID no encontrado.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ID inválido.");
                }
            }
        });

        frame.setVisible(true);
    }
}
